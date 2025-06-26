package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.password.EmailRequest;
import com.pet_project.backend_server.dto.request.password.ResetPasswordRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface ResetPasswordFacade {

    AuthResponse resetPasswordByEmail(EmailRequest request);
    boolean verifyAndReset(String email, String code);
    AuthResponse resetPassword(ResetPasswordRequest request, HttpServletResponse response);
}
