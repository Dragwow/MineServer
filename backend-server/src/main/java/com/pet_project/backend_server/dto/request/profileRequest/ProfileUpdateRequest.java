package com.pet_project.backend_server.dto.request.profileRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.pet_project.backend_server.util.ValidatorsUtil.GITHUB_URL_REGEX;

@Data
@Schema(description = "Request for update profile")
public class ProfileUpdateRequest {

    @Schema(description = "First name")
    private String firstName;

    @Schema(description = "Last name")
    private String lastName;

    @Schema(description = "Bio")
    private String bio;

    @Pattern(regexp = GITHUB_URL_REGEX)
    @Schema(description = "GitHub URL", example = "https://github.com/user")
    private String github;
}
