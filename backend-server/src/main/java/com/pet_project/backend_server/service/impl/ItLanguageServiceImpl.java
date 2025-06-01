package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.itLanguage.ItLanguageRepository;
import com.pet_project.backend_server.service.ItLanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItLanguageServiceImpl implements ItLanguageService {

    private ItLanguageRepository itLanguageRepository;

    @Override
    public void addItLanguage(ItLanguage itLanguage) {
        itLanguageRepository.save(itLanguage);
    }

    @Override
    public List<ItLanguage> getLanguageByUserProfileId(Long userProfileId) {
        return itLanguageRepository.findByUserProfileId(userProfileId);
    }

    @Override
    public void deleteItLanguage(Long itLanguageId) {

    }

    @Override
    public void deleteAllByUserProfileId(Long userProfileId) {

    }

    @Override
    public List<ItLanguage> findByUserProfile(UserProfile userProfile) {
        return itLanguageRepository.findByUserProfile(userProfile);
    }

}
