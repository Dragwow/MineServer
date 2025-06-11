package com.pet_project.backend_server.service;


import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.user.User;

import java.util.List;

public interface LanguageService {
     void addLanguage(Language language);
     void updateLanguage(Language language);
    List<Language> getLanguageByUserId(Long userId);
    void deleteLanguage(Long languageId);
    void deleteAllByUserId(Long userId);
    List<Language> findByUser(User user);
}
