package com.example.backend.services;

import com.example.backend.entities.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
