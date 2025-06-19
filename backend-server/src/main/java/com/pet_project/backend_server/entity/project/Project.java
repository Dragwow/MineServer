package com.pet_project.backend_server.entity.project;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.offer.OfferComments;
import com.pet_project.backend_server.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    private String nameProject;
    private String description;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String createdBy;
    private String linkGit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProjectComment> projectComments;
}
