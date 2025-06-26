package org.example.moreeduceoriginapplication.configuration;

import jakarta.persistence.Id;
import org.example.moreeduceoriginapplication.model.Teacher;
import org.example.moreeduceoriginapplication.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    private final org.springframework.mail.javamail.JavaMailSender mailSender;

    @Autowired
    public EmailService(org.springframework.mail.javamail.JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    TeacherRepo teacherRepo;

//    public void findemail(Long id){
//        Optional<Teacher> byId = teacherRepo.findById(id);
//        Teacher teacher = byId.get();
//        String email = teacher.getEmail();
//    }

    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("normatovhakimjon07@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

        System.out.println("Email sent success");
    }



}