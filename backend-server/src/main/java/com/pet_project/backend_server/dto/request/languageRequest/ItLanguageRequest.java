package com.pet_project.backend_server.dto.request.languageRequest;

import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.itLanguage.ProgrammingLanguageType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItLanguageRequest {

    @NotNull
    @Schema(description = "Type")
    private ProgrammingLanguageType language;

    @NotNull
    @Schema(description = "level")
    private LanguageLevel level;
}
