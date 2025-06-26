package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.entity.user.RoleUser;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.exception.IncorrectPasswordException;
import com.pet_project.backend_server.facade.RegistrationFacade;
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
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final TemplateUserService templateUserService;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    @Override
    public AuthResponse register(RegRequest request) {
        if (!request.getPassword().equals(request.getSecondPassword())) throw new IncorrectPasswordException("Enter correct password");

        templateUserService.saveTempUser(request);
        otpService.sendOtp(request.getEmail());
        return new AuthResponse("Otp code send" + request.getEmail());
    }

    @Override
    public AuthResponse verifyOtpAndRegister(String email, String code, HttpServletResponse response) {
        if (!otpService.verifyOtp(email, code)){
            throw new RuntimeException("Invalid verification code");
        }

        RegRequest request = templateUserService.getTempUserByEmail(email);
        if (request == null){
            throw new RuntimeException("Data of registration not founded");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(RoleUser.ROLE_USER);
        userService.create(user);

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
