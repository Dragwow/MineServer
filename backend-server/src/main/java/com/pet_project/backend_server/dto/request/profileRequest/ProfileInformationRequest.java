package com.pet_project.backend_server.dto.request.profileRequest;

import com.pet_project.backend_server.dto.request.languageRequest.ItLanguageRequest;
import com.pet_project.backend_server.dto.request.languageRequest.LanguageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.pet_project.backend_server.util.ValidatorsUtil.GITHUB_URL_REGEX;

@Getter
@Setter
@Schema(description = "Request to add information")
public class ProfileInformationRequest {

    @NotBlank(message = "can`t be blank")
    @Schema(description = "Bio")
    private String bio;

    @NotBlank(message = "can`t be blank")
    @Pattern(regexp = GITHUB_URL_REGEX, message = "Enter your GitHub link")
    @Schema(description = "GitHub URL", example = "https://github.com/user")
    private String github;

    @NotNull(message = "can`t be null")
    @NotEmpty(message = "can`t be empty")
    @Valid
    private List<ItLanguageRequest> itLanguage;

    @NotNull(message = "can`t be null")
    @NotEmpty(message = "can`t be empty")
    @Valid
    private List<LanguageRequest> language;

}
