package com.webshop.service;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
}
