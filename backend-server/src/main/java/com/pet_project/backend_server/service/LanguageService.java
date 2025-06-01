package com.pet_project.backend_server.service;


import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.userProfile.UserProfile;

import java.util.List;

public interface LanguageService {
     void addLanguage(Language language);
    List<Language> getLanguageByUserProfileId(Long userProfileId);
    void deleteLanguage(Long languageId);
    void deleteAllByUserProfileId(Long userProfileId);
    List<Language> findByUserProfile(UserProfile userProfile);
}
