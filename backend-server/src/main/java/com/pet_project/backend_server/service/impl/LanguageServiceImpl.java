package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.user.User;
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
    public void updateLanguage(Language language){
        languageRepository.save(language);
    }

    @Override
    public List<Language> getLanguageByUserId(Long userId) {
        return languageRepository.findByUserId(userId);
    }

    @Override
    public void deleteLanguage(Long languageId) {
        languageRepository.deleteById(languageId);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        languageRepository.deleteAllByUserId(userId);
    }

    @Override
    public List<Language> findByUser(User user) {
        return languageRepository.findByUser(user);
    }
}
