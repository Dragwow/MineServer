package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.entity.OTP.OtpEntry;
import com.pet_project.backend_server.repository.OTP.OtpEntryRepository;
import com.pet_project.backend_server.service.MailService;
import com.pet_project.backend_server.service.OtpService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpEntryRepository otpEntryRepository;
    private final MailService mailService;

    @Override
    @CachePut(value = "otpCache", key = "#email")
    public void sendOtp(String email) {
        String code = String.valueOf(new Random().nextInt(900000)+ 99999);
        otpEntryRepository.save(OtpEntry.builder()
                        .email(email)
                        .code(code)
                        .createdAt(LocalDateTime.now())
                        .expiresAt(LocalDateTime.now().plusMinutes(5))
                        .used(false)
                        .build());
        mailService.sendSimpleMessage(email, "Code", "You`re code" + code);
    }

    @CacheEvict(value = "otpCache", key = "#email")
    public void removeOtp(String email){ }

    @Transactional
    @Override
    public boolean verifyOtp(String email, String code) {
        Optional<OtpEntry> otpEntry = otpEntryRepository.findTopByEmailAndUsedFalseOrderByCreatedAtDesc(email);
        if (otpEntry.isEmpty()) return false;

        OtpEntry otp = otpEntry.get();
        if (!otp.getCode().equals(code)) return false;
        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) return false;

        otp.setUsed(true);
        otpEntryRepository.save(otp);
        return true;
    }
}
