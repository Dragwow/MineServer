package com.pet_project.backend_server.entity.project;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.user.User;
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
@Table(name = "project_comments")
public class ProjectComment extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User authorUsername;

    private LocalDateTime createdAt = LocalDateTime.now();
}
