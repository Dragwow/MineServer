package com.pet_project.backend_server.elastic.sync;

import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.elastic.document.QuerySearch;
import com.pet_project.backend_server.elastic.repository.OfferIndexRepository;
import com.pet_project.backend_server.elastic.repository.QuerySearchRepository;
import com.pet_project.backend_server.logger.LoggerLevel;
import com.pet_project.backend_server.logger.LoggerService;
import com.pet_project.backend_server.repository.data.OfferSearchDto;
import com.pet_project.backend_server.repository.offer.OfferRepository;
import com.pet_project.backend_server.store.SavedQuery;
import com.pet_project.backend_server.store.SavedQueryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElasticsearchSyncService {

    private final OfferIndexRepository offerIndexRepository;
    private final QuerySearchRepository querySearchRepository;
    private final SavedQueryRepository savedQueryRepository;
    private final LoggerService loggerService;
    private final OfferRepository offerRepository;

    @Scheduled(cron = "*/30 * * * * *")
    public void syncOffers() {
        System.out.println("ElasticsearchSyncService.sync");
        offerIndexRepository.deleteAll();
        List<OfferIndex> IndexList = getOfferIndex();
        if (!CollectionUtils.isEmpty(IndexList)) {
            loggerService.log(LoggerLevel.INFO, "indexList size " + IndexList.size());
            offerIndexRepository.saveAll(IndexList);
        }
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void findAllQuerySearchAndMoveToMOngo() {
        Iterable<QuerySearch> querySearchCollection = querySearchRepository.findAll();
        Set<String> querySearches = IterableUtils.toList(querySearchCollection)
                .stream()
                .map(QuerySearch::getQuery)
                .collect(Collectors.toSet());
        List<SavedQuery> savedQueries = querySearches.stream().map(SavedQuery::new).toList();
        savedQueryRepository.saveAll(savedQueries);
        querySearchRepository.deleteAll();

    }

    private List<OfferIndex> getOfferIndex() {
        List<OfferSearchDto> offerSearchDtoList = offerRepository.findAllOfferSearchDtoList();
        if (!CollectionUtils.isEmpty(offerSearchDtoList)) {
            return offerSearchDtoList
                    .stream()
                    .map(ps -> OfferIndex
                            .builder()
                            .offerId(String.valueOf(ps.offer().getId()))
                            .title(ps.offer().getTitle())
                            .description(ps.offer().getDescription())
                            .createdBy(ps.offer().getCreatedBy())
                            .status(ps.offer().getStatus())
                            .type(ps.offer().getType())
                            .build())
                    .toList();
        }
        return Collections.emptyList();
    }
}
