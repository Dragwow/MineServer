package com.pet_project.backend_server.dto.response.profileResponse;

import com.pet_project.backend_server.dto.response.languageResponse.ItLanguageResponse;
import com.pet_project.backend_server.dto.response.languageResponse.LanguageResponse;
import com.pet_project.backend_server.dto.response.offerResponse.OfferResponse;
import com.pet_project.backend_server.dto.response.projectResponse.ProjectResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "create profile response")
public class ProfileResponse {

    @Schema(description = "Username")
    private String username;

    @Schema(description = "FirstName")
    private String firstName;

    @Schema(description = "LastName")
    private String lastName;

    @Schema(description = "GitHub Url")
    private String gitHub;

    @Schema(description = "ItLanguage")
    private List<ItLanguageResponse> itLanguage;

    @Schema(description = "Language")
    private List<LanguageResponse> language;

    @Schema(description = "Offers")
    private List<OfferResponse> offers;

    @Schema(description = "Projects")
    private List<ProjectResponse> projects;

    @Schema(description = "User avatar url")
    private String avatarUrl;

    @Schema(description = "Bio")
    private String bio;

}