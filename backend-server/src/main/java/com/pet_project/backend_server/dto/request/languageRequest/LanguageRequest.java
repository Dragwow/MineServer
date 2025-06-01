package com.pet_project.backend_server.dto.request.languageRequest;

import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.language.LanguageType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LanguageRequest {

    @NotNull
    @Schema(description = "Language")
    private LanguageType language;

    @NotNull
    @Schema(description = "Level")
    private LanguageLevel level;
}
