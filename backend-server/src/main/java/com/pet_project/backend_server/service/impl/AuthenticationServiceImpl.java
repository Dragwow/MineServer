package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.entity.user.RoleUser;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.service.*;
import com.pet_project.backend_server.service.AuthenticationService;
import com.pet_project.backend_server.service.JwtService;
import com.pet_project.backend_server.util.CookieUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse register(RegRequest regRequest, HttpServletResponse response) {
        User user = new User();
        user.setUsername(regRequest.getUsername());
        user.setFirstName(regRequest.getFirstName());
        user.setLastName(regRequest.getLastName());
        user.setEmail(regRequest.getEmail());
        user.setPassword(passwordEncoder.encode(regRequest.getPassword()));
        user.setRole(RoleUser.ROLE_USER);
        userService.create(user);
        return getAuthResponse(user, response);
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return getAuthResponse(userDetails, response);
    }

    private AuthResponse getAuthResponse(UserDetails userDetails, HttpServletResponse response) {
        final String token = jwtService.generateToken(userDetails);
        CookieUtils.addCookie(response, "jwt", token, 60 * 60 * 24);
        return new AuthResponse(token);
    }
}
