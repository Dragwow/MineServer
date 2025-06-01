package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.language.LanguageRepository;
import com.pet_project.backend_server.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public void addLanguage(Language language) {
        languageRepository.save(language);
    }

    @Override
    public List<Language> getLanguageByUserProfileId(Long userProfileId) {
        return languageRepository.findByUserProfileId(userProfileId);
    }

    @Override
    public void deleteLanguage(Long languageId) {

    }

    @Override
    public void deleteAllByUserProfileId(Long userProfileId) {

    }

    @Override
    public List<Language> findByUserProfile(UserProfile userProfile) {
        return languageRepository.findByUserProfile(userProfile);
    }
}
