package com.pet_project.backend_server.dto.request.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    private String email;

    private String newPassword;

    private String secondNewPassword;
}
