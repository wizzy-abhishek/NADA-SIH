package com.ai.aiml10.controller;

import com.ai.aiml10.service.EmailService;
import jakarta.validation.GroupSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService ;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/mail")
    private void sendMail(){
        emailService.sendMail("pes8102064158@gmail.com" , "subjects" , "bodies");
    }
}
