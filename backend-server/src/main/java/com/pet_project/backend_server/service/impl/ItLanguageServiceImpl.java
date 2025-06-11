package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.user.User;
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
    public void updateItLanguage(ItLanguage itLanguage){
        itLanguageRepository.save(itLanguage);
    }

    @Override
    public List<ItLanguage> getLanguageByUserId(Long userId) {
        return itLanguageRepository.findByUserId(userId);
    }

    @Override
    public void deleteItLanguage(Long itLanguageId) {
        itLanguageRepository.deleteById(itLanguageId);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        itLanguageRepository.deleteAllByUserId(userId);
    }

    @Override
    public List<ItLanguage> findByUser(User user) {
        return itLanguageRepository.findByUser(user);
    }

}
