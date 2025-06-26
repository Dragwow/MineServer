package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;

public interface TemplateUserService {

    RegRequest saveTempUser(RegRequest request);
    AuthRequest saveTempUserInLogin(String email, AuthRequest request);
    AuthResponse saveTempEmail(String email);
    RegRequest getTempUserByEmail(String email);
    AuthRequest getTempUserByEmailInLogin(String email);
    void deleteTempUser(String email);
}
