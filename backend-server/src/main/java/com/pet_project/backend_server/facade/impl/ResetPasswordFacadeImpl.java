package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.password.EmailRequest;
import com.pet_project.backend_server.dto.request.password.ResetPasswordRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.exception.IncorrectPasswordException;
import com.pet_project.backend_server.facade.ResetPasswordFacade;
import com.pet_project.backend_server.service.JwtService;
import com.pet_project.backend_server.service.OtpService;
import com.pet_project.backend_server.service.TemplateUserService;
import com.pet_project.backend_server.service.UserService;
import com.pet_project.backend_server.util.CookieUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResetPasswordFacadeImpl implements ResetPasswordFacade {

    private final UserService userService;
    private final OtpService otpService;
    private final TemplateUserService templateUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse resetPasswordByEmail(EmailRequest request) {
        templateUserService.saveTempEmail(request.getEmail());
        otpService.sendOtp(request.getEmail());
        return new AuthResponse("Otp code send" + request.getEmail());
    }

    @Override
    public boolean verifyAndReset(String email, String code) {
        if (!otpService.verifyOtp(email, code)){
            throw new RuntimeException("Invalid verification code");
        }
        else return true;
    }

    @Override
    public AuthResponse resetPassword(ResetPasswordRequest request,HttpServletResponse response) {
        User user = userService.findByEmail(request.getEmail());

        if (!request.getNewPassword().equals(request.getSecondNewPassword())) throw new IncorrectPasswordException("Enter correct password");
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userService.resetPassword(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return getAuthResponse(userDetails, response);
    }

    private AuthResponse getAuthResponse(UserDetails userDetails, HttpServletResponse response) {
        final String token = jwtService.generateToken(userDetails);
        CookieUtils.addCookie(response, "jwt", token, 60 * 60 * 24);
        return new AuthResponse(token);
    }


}
