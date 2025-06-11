package com.pet_project.backend_server.entity.itLanguage;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "it_language")
public class ItLanguage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgrammingLanguageType languageType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LanguageLevel languageLevel;



}
