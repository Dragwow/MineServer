package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.authRequest.VerifyOtpRequest;
import com.pet_project.backend_server.dto.request.password.EmailRequest;
import com.pet_project.backend_server.dto.request.password.ResetPasswordRequest;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.dto.response.auth.AuthResponse;
import com.pet_project.backend_server.facade.ResetPasswordFacade;
import com.pet_project.backend_server.util.CookieUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication controller", description = "contains authentication method")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final ResetPasswordFacade resetPasswordFacade;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        CookieUtils.deleteCookie(response, "jwt");
        return  ResponseEntity.ok("Logout");
    }

    @PostMapping("reset-password/email")
    public ResponseEntity<ResponseContainer<AuthResponse>> takeEmail(
            @RequestBody
            EmailRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(resetPasswordFacade.resetPasswordByEmail(request)));
    }

    @PostMapping("/reset-password/verify")
    public ResponseEntity<ResponseContainer<?>> verifyEmail(
            @RequestBody
            VerifyOtpRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(resetPasswordFacade.verifyAndReset(request.getEmail(), request.getEmail())));
    }

    @PostMapping("/reset-password/password")
    public ResponseEntity<ResponseContainer<AuthResponse>> resetPassword(
            @RequestBody
            ResetPasswordRequest request,
            HttpServletResponse response){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(resetPasswordFacade.resetPassword(request,response)));
    }


}
