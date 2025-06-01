package com.pet_project.backend_server.dto.response;

import com.pet_project.backend_server.entity.userProfile.UserProfile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Authentication response")
public class AuthResponse {

    @Schema(description = "Authentication token")
    private String token;

}
