package com.pet_project.backend_server.elastic.sync;

import com.pet_project.backend_server.elastic.document.*;
import com.pet_project.backend_server.elastic.repository.*;
import com.pet_project.backend_server.logger.LoggerLevel;
import com.pet_project.backend_server.logger.LoggerService;
import com.pet_project.backend_server.repository.data.OfferSearchDto;
import com.pet_project.backend_server.repository.data.ProjectSearchDto;
import com.pet_project.backend_server.repository.data.UserProfileSearchDto;
import com.pet_project.backend_server.repository.offer.OfferRepository;
import com.pet_project.backend_server.repository.projectRepository.ProjectRepository;
import com.pet_project.backend_server.repository.userProfile.UserProfileRepository;
import com.pet_project.backend_server.store.SavedQuery;
import com.pet_project.backend_server.store.SavedQueryRepository;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElasticsearchSyncService {

    private final OfferIndexRepository offerIndexRepository;
    private final QuerySearchRepository querySearchRepository;
    private final SavedQueryRepository savedQueryRepository;
    private final LoggerService loggerService;
    private final OfferRepository offerRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserProfileIndexRepository userProfileIndexRepository;
    private final ProjectRepository projectRepository;
    private final ProjectIndexRepository projectIndexRepository;

    @Transactional
    @Scheduled(cron = "*/30 * * * * *")
    public void syncOffers() {
        System.out.println("ElasticsearchSyncService.sync");
        offerIndexRepository.deleteAll();
        List<OfferIndex> IndexList = getOfferIndex();
        if (CollectionUtils.isNotEmpty(IndexList)) {
            loggerService.log(LoggerLevel.INFO, "indexList size " + IndexList.size());
            offerIndexRepository.saveAll(IndexList);
        }
    }

    @Transactional
    @Scheduled(cron = "*/30 * * * * *")
    public void syncProfiles(){
        System.out.println("ElasticsearchSyncService.sync");
        userProfileIndexRepository.deleteAll();
        List<UserProfileIndex> userProfileIndex = getUserProfileIndex();
        if (CollectionUtils.isNotEmpty(userProfileIndex)) {
            loggerService.log(LoggerLevel.INFO, "userProfileIndex size " + userProfileIndex.size());

            userProfileIndexRepository.saveAll(userProfileIndex);
        }
    }

    @Transactional
    @Scheduled(cron = "*/30 * * * * *")
    public void syncProjects() {
        System.out.println("ElasticsearchSyncService.sync");
        projectIndexRepository.deleteAll();
        List<ProjectIndex> projectIndex = getProjectIndex();
        if (CollectionUtils.isNotEmpty(projectIndex)) {
            loggerService.log(LoggerLevel.INFO, "indexList size " + projectIndex.size());
            projectIndexRepository.saveAll(projectIndex);
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

    @Transactional
    protected List<OfferIndex> getOfferIndex() {
        List<OfferSearchDto> offerSearchDtoList = offerRepository.findAllOfferSearchDtoList();
        if (CollectionUtils.isNotEmpty(offerSearchDtoList)) {
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

    @Transactional
    protected List<UserProfileIndex> getUserProfileIndex() {
        List<UserProfileSearchDto> userProfileSearchDtoList = userProfileRepository.findAllUserProfileSearchDto();
        if (CollectionUtils.isNotEmpty(userProfileSearchDtoList)) {
            return userProfileSearchDtoList
                    .stream()
                    .map(ps -> UserProfileIndex
                            .builder()
                            .profileId(String.valueOf(ps.userProfile().getId()))
                            .bio(ps.userProfile().getBio())
                            .github(ps.userProfile().getGitHub())
                            .itLanguage(ps.userProfile().getItLanguages())
                            .language(ps.userProfile().getLanguages())
                            .username(ps.user().getUsername())
                            .age(ps.user().getAge())
                            .firstName(ps.user().getFirstName())
                            .lastName(ps.user().getLastName())
                            .build()
                    ).toList();
        }
        return Collections.emptyList();
    }

    @Transactional
    protected List<ProjectIndex> getProjectIndex() {
        List<ProjectSearchDto> projectSearchDtoList = projectRepository.findAllProjectSearchDto();
        if (CollectionUtils.isNotEmpty(projectSearchDtoList)) {
            return projectSearchDtoList
                    .stream()
                    .map(ps -> ProjectIndex
                            .builder()
                            .projectId(String.valueOf(ps.project().getId()))
                            .projectName(ps.project().getNameProject())
                            .createdBy(ps.project().getCreatedBy())
                            .description(ps.project().getDescription())
                            .build()
                    ).toList();
        }
        return Collections.emptyList();
    }
}
