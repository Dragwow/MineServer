package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface RegistrationFacade {

    AuthResponse register(RegRequest regRequest );
    AuthResponse verifyOtpAndRegister(String email, String code, HttpServletResponse response);
}
