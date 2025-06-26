package com.pet_project.backend_server.repository.OTP;

import com.pet_project.backend_server.entity.OTP.OtpEntry;
import com.pet_project.backend_server.repository.BaseRepository;

import java.util.Optional;

public interface OtpEntryRepository extends BaseRepository<OtpEntry> {
    Optional<OtpEntry> findTopByEmailAndUsedFalseOrderByCreatedAtDesc(String email);
}
