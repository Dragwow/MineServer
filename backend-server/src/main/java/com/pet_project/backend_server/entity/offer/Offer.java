package com.pet_project.backend_server.entity.offer;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "text")
    private String description;

    private String createdBy;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @Enumerated(EnumType.STRING)
    private OfferType type;

    @Column(name = "created_at", columnDefinition = "timestamp(6)")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;
}
