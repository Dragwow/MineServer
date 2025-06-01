package com.pet_project.backend_server.entity.userProfile;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_profiles")
public class UserProfile extends BaseEntity {

    private String avatarUrl;
    private String bio;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY)
    private List<Language> languages;
    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY)
    private List<ItLanguage> itLanguages;

    private String gitHub;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "userProfile" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Offer> offers;

    private boolean receiveEmailNotification;
    private boolean receiveSMSNotification;

    private boolean twoFactorEnabled;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
