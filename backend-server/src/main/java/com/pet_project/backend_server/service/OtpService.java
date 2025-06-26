package com.pet_project.backend_server.service;

public interface OtpService {

    void sendOtp(String email);
    boolean verifyOtp(String email, String code);
    void removeOtp(String email);
}
