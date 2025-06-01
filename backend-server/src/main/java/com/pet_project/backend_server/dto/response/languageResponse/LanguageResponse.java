package com.pet_project.backend_server.dto.response.languageResponse;

import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.language.LanguageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguageResponse {

    @Schema(description = "Language")
    private LanguageType language;

    @Schema(description = "Level")
    private LanguageLevel level;

    public LanguageResponse(Language language) {
//        language.setId(language.getId());
        this.language = language.getLanguageType();
        this.level = language.getLanguageLevel();
    }

}
