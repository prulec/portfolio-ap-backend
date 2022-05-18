package com.argprograma.portfolio.controller;

import com.argprograma.portfolio.dto.EmailData;
import com.argprograma.portfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    
    @Autowired
    private EmailService emailService;
    
    @PostMapping ("sendcontact")
    public void sendMail(@RequestBody EmailData data) {
        emailService.sendEmail(data.getReplyTo(),
                               data.getSubject(), 
                               data.getBody());
        
    }
    
}
