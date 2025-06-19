package com.pet_project.backend_server.entity.user;

import com.pet_project.backend_server.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_comments")
public class UserComments extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private User userProfile;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User authorUsername;

    private LocalDateTime createdAt = LocalDateTime.now();
}