package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.service.*;
import com.pet_project.backend_server.facade.AuthenticationFacade;
import com.pet_project.backend_server.service.JwtService;
import com.pet_project.backend_server.util.CookieUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final OtpService otpService;
    private final TemplateUserService templateUserService;

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        User user = userService.findByUsername(request.getUsername());
        templateUserService.saveTempUserInLogin(user.getEmail(), request);
        otpService.sendOtp(user.getEmail());
        return new AuthResponse("Otp code send " + user.getEmail());
    }

    @Override
    public AuthResponse verifyOtpAndLogin(String email, String code, HttpServletResponse response) {
        if (!otpService.verifyOtp(email, code)){
            throw new RuntimeException("Invalid verification code");
        }

        AuthRequest request = templateUserService.getTempUserByEmailInLogin(email);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        templateUserService.deleteTempUser(email);
        return getAuthResponse(userDetails, response);

    }

    private AuthResponse getAuthResponse(UserDetails userDetails, HttpServletResponse response) {
        final String token = jwtService.generateToken(userDetails);
        CookieUtils.addCookie(response, "jwt", token, 60 * 60 * 24);
        return new AuthResponse(token);
    }


}
