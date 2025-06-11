package com.pet_project.backend_server.repository.data;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.itLanguage.ProgrammingLanguageType;

public record ItLanguageSearchDto(ItLanguage itLanguage, ProgrammingLanguageType languageType, LanguageLevel languageLevel) {
}
