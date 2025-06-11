package com.pet_project.backend_server.entity.project;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    private String nameProject;
    private String description;

    @Column(name = "created_at", columnDefinition = "timestamp(6)")
    private LocalDateTime createdAt;

    private String updatedAt;

    private String createdBy;
    private String linkGit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
