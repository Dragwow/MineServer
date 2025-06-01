package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.authRequest.AuthRequest;
import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.response.AuthResponse;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication controller", description = "contains authentication method")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseContainer<AuthResponse>> register(
            @Valid
            @RequestBody
            RegRequest regRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseContainer<>(authenticationService.register(regRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseContainer<AuthResponse>> login(
            @Valid
            @RequestBody
            AuthRequest authRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(authenticationService.authenticate(authRequest)));
    }

}
