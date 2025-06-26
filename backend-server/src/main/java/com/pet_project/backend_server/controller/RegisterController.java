package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.authRequest.RegRequest;
import com.pet_project.backend_server.dto.request.authRequest.VerifyOtpRequest;
import com.pet_project.backend_server.dto.request.profileRequest.ProfileInformationRequest;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.facade.ProfileFacade;
import com.pet_project.backend_server.facade.RegistrationFacade;
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

import java.security.Principal;

@Tag(name = "Registration controller", description = "contains registration method")
@RestController
@RequestMapping("/api/auth/register")
@AllArgsConstructor
public class RegisterController {

    private final RegistrationFacade registrationFacade;
    private final ProfileFacade profileFacade;

    @PostMapping("/init")
    public ResponseEntity<?> register(
            @Valid
            @RequestBody
            RegRequest regRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseContainer<>(registrationFacade.register(regRequest)));
    }

    @PostMapping("/verify")
    public ResponseEntity<ResponseContainer<AuthResponse>> verifyRegister(
            @RequestBody
            VerifyOtpRequest request,
            HttpServletResponse response){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(registrationFacade.verifyOtpAndRegister(request.getEmail(), request.getCode(), response)));

    }

    @PostMapping("/create-profile")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> addInf(
            @RequestBody
            ProfileInformationRequest request,
            Principal principal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new  ResponseContainer<>(profileFacade.addInformation(request, principal.getName())));
    }
}
