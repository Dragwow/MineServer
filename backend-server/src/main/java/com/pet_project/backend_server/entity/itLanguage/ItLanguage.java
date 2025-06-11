package com.pet_project.backend_server.entity.itLanguage;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "it_language")
public class ItLanguage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "language_type")
    private ProgrammingLanguageType languageType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "language_level")
    private LanguageLevel languageLevel;



}
