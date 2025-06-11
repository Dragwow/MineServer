package com.pet_project.backend_server.entity.language;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "language")
public class Language extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "language_type")
    private LanguageType languageType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "language_level")
    private LanguageLevel languageLevel;
}
