package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendTestEmail")
    public String sendTestEmail(@RequestParam String email) {
        try {
            emailService.sendSimpleMessage(
                email,
                "Test Subject",
                "This is a test email sent from Spring Boot."
            );
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}
