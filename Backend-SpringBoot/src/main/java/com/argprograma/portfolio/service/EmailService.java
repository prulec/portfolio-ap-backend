package com.argprograma.portfolio.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendEmail(String replyTo,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("prulec@gmail.com");
        message.setReplyTo(replyTo);
        message.setSubject(subject);
        message.setText(body);
        
        mailSender.send(message);
        
        System.out.println("Email sent!");
    }
}
