package com.pet_project.backend_server.entity.language;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "language")
public class Language extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LanguageType languageType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LanguageLevel languageLevel;
}
