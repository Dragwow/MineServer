package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.service.TemplateUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TemplateUserServiceImpl implements TemplateUserService {


    @Override
    @CachePut(value = "tempUserCache", key = "#request.email")
    public RegRequest saveTempUser(RegRequest request) {
        return request;
    }

    @Override
    @CachePut(value = "tempUserCache", key = "#email")
    public AuthRequest saveTempUserInLogin(String email, AuthRequest request) {
        return request;
    }

    @Override
    @CachePut(value = "tempUserCache", key = "#email")
    public AuthResponse saveTempEmail(String email) {
        return new AuthResponse(email);
    }

    @Override
    @Cacheable(value = "tempUserCache", key = "#email")
    public RegRequest getTempUserByEmail(String email) {
        return null;
    }

    @Override
    @Cacheable(value = "tempUserCache", key = "#email")
    public AuthRequest getTempUserByEmailInLogin(String email) {
        return null;
    }

    @Override
    @CacheEvict(value = "tempUserCache", key = "#email")
    public void deleteTempUser(String email) {}
}
