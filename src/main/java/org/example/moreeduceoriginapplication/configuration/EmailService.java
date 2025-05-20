package org.example.moreeduceoriginapplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final org.springframework.mail.javamail.JavaMailSender mailSender;

    @Autowired
    public EmailService(org.springframework.mail.javamail.JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("gmail.com");
        message.setTo(toEmail);
        message.setSubject("Email verification");
        message.setText(body);
        mailSender.send(message);

        System.out.println("Email sent successfully!");
    }
}