package com.pet_project.backend_server.dto.response.languageResponse;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.itLanguage.ItLanguageType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItLanguageResponse {

    @Schema(description = "Type")
    private ItLanguageType language;

    @Schema(description = "level")
    private LanguageLevel level;

    public ItLanguageResponse(ItLanguage itLanguage) {
//        itLanguage.setLanguage(language);
        this.language = itLanguage.getLanguageType();
        this.level = itLanguage.getLanguageLevel();
    }

}
