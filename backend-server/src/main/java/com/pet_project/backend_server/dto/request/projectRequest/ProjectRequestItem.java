package com.pet_project.backend_server.dto.request.projectRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.pet_project.backend_server.util.ValidatorsUtil.GITHUB_URL_REGEX;

@Getter
@Setter
@AllArgsConstructor
public class ProjectRequestItem {

    @NotNull(message = "can`t be null")
    @NotEmpty(message = "can`t be empty")
    @Schema(description = "Name project")
    private String nameProject;

    @NotNull(message = "can`t be null")
    @NotEmpty(message = "can`t be empty")
    @Schema(description = "Description")
    private String descriptionProject;


    @NotBlank(message = "Enter your project link")
    @Pattern(regexp = GITHUB_URL_REGEX, message = "Enter your GitHub link")
    @Schema(description = "Project link")
    private String linkGit;

}