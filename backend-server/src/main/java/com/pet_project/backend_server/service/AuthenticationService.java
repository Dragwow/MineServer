package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.AuthResponse;

public interface AuthenticationService {

    AuthResponse register(RegRequest regRequest);
    AuthResponse authenticate(AuthRequest authRequest);
}
