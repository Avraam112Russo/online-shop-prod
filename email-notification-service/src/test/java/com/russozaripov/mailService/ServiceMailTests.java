package com.russozaripov.mailService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

@ExtendWith(MockitoExtension.class)
public class ServiceMailTests {
    @Mock
    private JavaMailSender javaMailSender;
    @InjectMocks
    private ServiceMail serviceMail;

    @DisplayName("Junit test for send mail operation.")
    @Test
    public void givenEmailSubjectText_whenSendMessage_thenReturnNothing(){
        //given
        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        BDDMockito.given(javaMailSender.createMimeMessage()).willReturn(mimeMessage);
        //when
        serviceMail.sendMessage("russo@mail.com", "Subject", "text...");
        //then
        Mockito.verify(javaMailSender, Mockito.times(1)).send(ArgumentMatchers.any(MimeMessage.class));

    }
}
