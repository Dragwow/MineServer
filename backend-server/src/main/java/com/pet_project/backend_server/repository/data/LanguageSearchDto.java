package com.pet_project.backend_server.repository.data;

import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.language.LanguageType;

public record LanguageSearchDto(Language language, LanguageType languageType, LanguageLevel languageLevel) {
}
