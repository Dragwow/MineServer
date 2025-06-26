package com.pet_project.backend_server.entity.OTP;

import com.pet_project.backend_server.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_entries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpEntry extends BaseEntity {

    private String email;

    private String code;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private boolean used;
}
