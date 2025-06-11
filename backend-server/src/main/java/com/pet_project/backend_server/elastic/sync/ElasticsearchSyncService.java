package com.pet_project.backend_server.elastic.sync;

import com.pet_project.backend_server.elastic.document.*;
import com.pet_project.backend_server.elastic.repository.*;
import com.pet_project.backend_server.logger.LoggerLevel;
import com.pet_project.backend_server.logger.LoggerService;
import com.pet_project.backend_server.repository.data.*;
import com.pet_project.backend_server.repository.itLanguage.ItLanguageRepository;
import com.pet_project.backend_server.repository.language.LanguageRepository;
import com.pet_project.backend_server.repository.offer.OfferRepository;
import com.pet_project.backend_server.repository.projectRepository.ProjectRepository;
import com.pet_project.backend_server.repository.user.UserRepository;
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
    private final ProjectRepository projectRepository;
    private final ProjectIndexRepository projectIndexRepository;
    private final ItLanguageRepository itLanguageRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final UserIndexRepository userIndexRepository;

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
        userIndexRepository.deleteAll();
        List<UserIndex> userIndex = getUserIndex();
        if (CollectionUtils.isNotEmpty(userIndex)) {
            loggerService.log(LoggerLevel.INFO, "userProfileIndex size " + userIndex.size());

            userIndexRepository.saveAll(userIndex);
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
    public void findAllQuerySearchAndMoveToMongo() {
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
    protected List<UserIndex> getUserIndex() {
        List<UserSearchDto> userProfileSearchDtoList = userRepository.findAllUserSearchDto();
        if (CollectionUtils.isNotEmpty(userProfileSearchDtoList)) {
            return userProfileSearchDtoList
                    .stream()
                    .map(ps -> UserIndex
                            .builder()
                            .userId(String.valueOf(ps.user().getId()))
                            .username(ps.user().getUsername())
                            .firstName(ps.user().getFirstName())
                            .lastName(ps.user().getLastName())
                            .age(ps.user().getAge())
                            .bio(ps.user().getBio())
                            .github(ps.user().getGitHub())
                            .itLanguages(getItLanguagesByProfileId(ps.user().getId()))
                            .languages(getLanguagesByProfileId(ps.user().getId()))
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

    private List<ItLanguageIndex> getItLanguagesByProfileId(Long userProfileId){
        List<ItLanguageSearchDto> itLanguageSearchDtoList = itLanguageRepository.findAllItLanguageSearchDto();
        if (CollectionUtils.isNotEmpty(itLanguageSearchDtoList)){
            return itLanguageSearchDtoList
                    .stream()
                    .map(si -> ItLanguageIndex
                            .builder()
                            .itLanguageId(String.valueOf(si.itLanguage().getId()))
                            .languageType(si.itLanguage().getLanguageType())
                            .languageLevel(si.itLanguage().getLanguageLevel())
                            .build()
                    ).toList();
        }
        return Collections.emptyList();
    }

    private List<LanguageIndex> getLanguagesByProfileId(Long userProfileId){
        List<LanguageSearchDto> languageSearchDtoList = languageRepository.findAllLanguageSearchDto();
        if (CollectionUtils.isNotEmpty(languageSearchDtoList)){
            return languageSearchDtoList
                    .stream()
                    .map(si -> LanguageIndex
                            .builder()
                            .languageId(String.valueOf(si.language().getId()))
                            .languageType(si.language().getLanguageType())
                            .languageLevel(si.language().getLanguageLevel())
                            .build()
                    ).toList();
        }
        return Collections.emptyList();
    }


}
