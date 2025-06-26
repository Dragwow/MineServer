package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.request.authRequest.VerifyOtpRequest;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.facade.AuthenticationFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication controller", description = "contains authentication method")
@RestController
@RequestMapping("/api/auth/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/init")
    public ResponseEntity<?> login(
            @Valid
            @RequestBody
            AuthRequest authRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseContainer<>(authenticationFacade.authenticate(authRequest)));
    }

    @PostMapping("/verify")
    public ResponseEntity<ResponseContainer<AuthResponse>> verificationLogin(
            @RequestBody
            VerifyOtpRequest request,
            HttpServletResponse response){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(authenticationFacade.verifyOtpAndLogin(request.getEmail(), request.getCode(), response)));
    }


}
