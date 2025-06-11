package com.pet_project.backend_server.entity.user;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.project.Project;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private int age;

    private String avatarUrl;
    private String bio;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Language> languages;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ItLanguage> itLanguages;

    private String gitHub;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Offer> offers;

    private boolean receiveEmailNotification;
    private boolean receiveSMSNotification;

    private boolean twoFactorEnabled;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUser role;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    private Boolean enabled;

    public User() {
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
        enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}