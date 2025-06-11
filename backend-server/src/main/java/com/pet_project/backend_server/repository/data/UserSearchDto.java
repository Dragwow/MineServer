package com.pet_project.backend_server.repository.data;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.user.User;

public record UserSearchDto(User user, ItLanguage itLanguages, Language languages) {
}
