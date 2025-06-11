package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    AuthResponse register(RegRequest regRequest, HttpServletResponse response);
    AuthResponse authenticate(AuthRequest authRequest, HttpServletResponse response);
}
