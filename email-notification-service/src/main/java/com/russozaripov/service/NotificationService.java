package com.russozaripov.service;

import com.russozaripov.event.DTO.RequestOrderDTO;
import com.russozaripov.mailService.ServiceMail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final ServiceMail serviceMail;
//    @KafkaListener(topics = "new-order", groupId = "order-group", p)
    @KafkaListener(topics = "newOrder", groupId = "notification-group")
    public void new_Order_Event_Message(RequestOrderDTO requestOrderDTO){
        log.info("email service received message from order service -> id: %s".formatted(requestOrderDTO.getOrderID()));
        String email = requestOrderDTO.getOrderInfoDTO().getEmail();
        serviceMail.sendMessage(
                // azamatabdiev1198@gmail.com
                email,
                "This is subject.",
                "NEW ORDER!!!"
        );
        log.info("email service sent message -> %s".formatted(email));
    }

}
