package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.profileRequest.ProfileInformationRequest;
import com.pet_project.backend_server.dto.request.profileRequest.ProfileUpdateRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataDeleteResponse;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.languageResponse.ItLanguageResponse;
import com.pet_project.backend_server.dto.response.languageResponse.LanguageResponse;
import com.pet_project.backend_server.dto.response.offerResponse.OfferResponse;
import com.pet_project.backend_server.dto.response.profileResponse.ProfileResponse;
import com.pet_project.backend_server.dto.response.projectResponse.ProjectResponse;
import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.facade.ProfileFacade;
import com.pet_project.backend_server.service.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor

public class ProfileFacadeImpl implements ProfileFacade {

    private final UserService userService;
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
    public ProfileResponse getProfile(String username) {
        User user = userService.findByUsername(username);

        List<ItLanguage> itLanguages = itLanguageService.findByUser(user);
        List<ItLanguageResponse> itLanguageResponses = itLanguages.stream()
                .map(ItLanguageResponse::new).toList();

        List<Language> languages = languageService.findByUser(user);
        List<LanguageResponse> languageResponses = languages.stream()
                .map(LanguageResponse::new).toList();

        List<Project> projects = projectService.findByUser(user);
        List<ProjectResponse> projectResponses = projects.stream()
                .map(ProjectResponse::new).toList();

        List<Offer> offers = offerService.findByUser(user);
        List<OfferResponse> offerResponses = offers.stream()
                .map(OfferResponse::new).toList();

        return ProfileResponse.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .bio(user.getBio())
                .gitHub(user.getGitHub())
                .itLanguage(itLanguageResponses)
                .language(languageResponses)
                .projects(projectResponses)
                .offers(offerResponses)
                .build();
    }

    @Override
    public DataSavedResponse addInformation(ProfileInformationRequest request, String username) {
        User user = userService.findByUsername(username);

        user.setBio(request.getBio());
        user.setGitHub(request.getGithub());

        userService.update(user);

        itLanguageService.deleteAllByUserId(user.getId());
        languageService.deleteAllByUserId(user.getId());

        List<ItLanguage> itLanguages = request.getItLanguage().stream()
                .map(dto -> {
                    ItLanguage language = new ItLanguage();
                    language.setUser(user);
                    language.setLanguageType(dto.getLanguage());
                    language.setLanguageLevel(dto.getLevel());
                    return language;
                }).toList();
        itLanguages.forEach(itLanguageService::addItLanguage);

        List<Language> languages = request.getLanguage().stream()
                .map(dto -> {
                    Language language = new Language();
                    language.setUser(user);
                    language.setLanguageType(dto.getLanguage());
                    language.setLanguageLevel(dto.getLevel());
                    return language;
                }).toList();
        languages.forEach(languageService::addLanguage);


        return new DataSavedResponse();
    }

    @Override
    public DataSavedResponse updateProfile(ProfileUpdateRequest request) {
        User user = new User();

        if (request.getFirstName() != null){
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null){
            user.setLastName(request.getLastName());
        }
        if (request.getBio() != null){
            user.setBio(request.getBio());
        }
        if (request.getGithub() != null){
            user.setGitHub(request.getGithub());
        }
        userService.update(user);

        return new DataSavedResponse();
    }

    @Override
    public DataDeleteResponse deleteProfile(Long id) {
        User user = userService.findById(id);
        if (user.isCredentialsNonExpired()){
            userService.delete(id);
            projectService.deleteAllByUSerId(id);
            offerService.deleteAllByUserId(id);
            languageService.deleteAllByUserId(id);
            itLanguageService.deleteAllByUserId(id);
        }
        return new DataDeleteResponse();
    }
}
