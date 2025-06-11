package com.pet_project.backend_server.service.impl.searchImpl;

import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.elastic.repository.OfferIndexRepository;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import com.pet_project.backend_server.service.OfferSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;


import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferSearchServiceImpl implements OfferSearchService {

    private final OfferIndexRepository offerIndexRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<OfferIndex> searchByStatus(OfferStatus status){
        return offerIndexRepository.findByStatus(status);
    }

    @Override
    public List<OfferIndex> searchByType(OfferType type){
        return offerIndexRepository.findByType(type);
    }

    @Override
    public List<OfferIndex> search(String title, String description, String username) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if (title != null && !title.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("title", title));
        }

        if (description != null && !description.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("description", description));
        }

        if (username != null && !username.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("createdBy", username));
        }

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return elasticsearchOperations
                .search(searchQuery, OfferIndex.class)
                .map(SearchHit::getContent)
                .toList();
    }
}
