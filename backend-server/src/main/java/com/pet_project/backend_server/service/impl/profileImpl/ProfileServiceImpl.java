package com.pet_project.backend_server.service.impl.profileImpl;

import com.pet_project.backend_server.dto.request.profileRequest.ProfileInformationRequest;
import com.pet_project.backend_server.dto.response.*;
import com.pet_project.backend_server.dto.response.languageResponse.ItLanguageResponse;
import com.pet_project.backend_server.dto.response.languageResponse.LanguageResponse;
import com.pet_project.backend_server.dto.response.offerResponse.OfferResponse;
import com.pet_project.backend_server.dto.response.projectResponse.ProjectResponse;
import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.service.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor

public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final ItLanguageService itLanguageService;
    private final LanguageService languageService;
    private final ProjectService projectService;
    private final OfferService offerService;

    @Override
    @Cacheable(
            value = "profileCache",
            key = "T(org.springframework.security.core.context.SecurityContextHolder)" +
                    ".getContext()" +
                    ".getAuthentication()" +
                    ".getName()")
    public ProfileResponse getProfile() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsername(username);

        UserProfile userProfile = user.getProfile();

        List<ItLanguage> itLanguages = itLanguageService.findByUserProfile(userProfile);
        List<ItLanguageResponse> itLanguageResponses = itLanguages.stream()
                .map(ItLanguageResponse::new).toList();

        List<Language> languages = languageService.findByUserProfile(userProfile);
        List<LanguageResponse> languageResponses = languages.stream()
                .map(LanguageResponse::new).toList();

        List<Project> projects = projectService.findByUserProfile(userProfile);
        List<ProjectResponse> projectResponses = projects.stream()
                .map(ProjectResponse::new).toList();

        List<Offer> offers = offerService.findByUserProfile(userProfile);
        List<OfferResponse> offerResponses = offers.stream()
                .map(OfferResponse::new).toList();

        return ProfileResponse.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .bio(userProfile.getBio())
                .gitHub(userProfile.getGitHub())
                .itLanguage(itLanguageResponses)
                .language(languageResponses)
                .projects(projectResponses)
                .offers(offerResponses)
                .build();
    }

    @Override
    public DataSavedResponse addInformation(ProfileInformationRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsername(username);

        UserProfile userProfile = user.getProfile();
        userProfile.setBio(request.getBio());
        userProfile.setGitHub(request.getGithub());
        userProfileService.create(userProfile);

        itLanguageService.deleteAllByUserProfileId(userProfile.getId());
        languageService.deleteAllByUserProfileId(userProfile.getId());

        List<ItLanguage> itLanguages = request.getItLanguage().stream()
                .map(dto -> {
                    ItLanguage language = new ItLanguage();
                    language.setUserProfile(userProfile);
                    language.setLanguageType(dto.getLanguage());
                    language.setLanguageLevel(dto.getLevel());
                    return language;
                }).toList();
        itLanguages.forEach(itLanguageService::addItLanguage);

        List<Language> languages = request.getLanguage().stream()
                .map(dto -> {
                    Language language = new Language();
                    language.setUserProfile(userProfile);
                    language.setLanguageType(dto.getLanguage());
                    language.setLanguageLevel(dto.getLevel());
                    return language;
                }).toList();
        languages.forEach(languageService::addLanguage);


        return new DataSavedResponse();
    }
}
