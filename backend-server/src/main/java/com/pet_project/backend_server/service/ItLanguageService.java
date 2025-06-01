package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.userProfile.UserProfile;

import java.util.List;

public interface ItLanguageService {

    void addItLanguage(ItLanguage itLanguage);
    List<ItLanguage> getLanguageByUserProfileId(Long userProfileId);
    void deleteItLanguage(Long itLanguageId);
    void deleteAllByUserProfileId(Long userProfileId);
    List<ItLanguage> findByUserProfile(UserProfile userProfile);


}
