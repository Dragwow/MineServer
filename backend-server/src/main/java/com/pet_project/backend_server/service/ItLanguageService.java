package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.user.User;

import java.util.List;

public interface ItLanguageService {

    void addItLanguage(ItLanguage itLanguage);
    void updateItLanguage(ItLanguage itLanguage);
    List<ItLanguage> getLanguageByUserId(Long userId);
    void deleteItLanguage(Long itLanguageId);
    void deleteAllByUserId(Long userId);
    List<ItLanguage> findByUser(User user);


}
