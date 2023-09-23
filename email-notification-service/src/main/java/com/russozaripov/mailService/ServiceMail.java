package com.russozaripov.mailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceMail {

    private final JavaMailSender javaMailSender;

    public void sendMessage(String toEmail, String subject, String text){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("avraam112russo@mail.com");
//        mailMessage.setTo(toEmail);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(text);
//
//        javaMailSender.send(mailMessage);
//        log.info("Message sent to email %s successfully.".formatted(toEmail));
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(text); // Установите в true, если вы хотите использовать HTML в письме
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
