package com.pet_project.backend_server.service;

public interface MailService {

    void sendSimpleMessage(String to, String subject, String text);
}
