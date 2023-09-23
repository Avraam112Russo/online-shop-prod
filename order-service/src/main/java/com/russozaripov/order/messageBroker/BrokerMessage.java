package com.russozaripov.order.messageBroker;

import com.russozaripov.order.DTO.RequestOrderDTO;
import com.russozaripov.order.event.NewOrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerMessage {
    private final KafkaTemplate<String, RequestOrderDTO> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(BrokerMessage.class);
    public void sendMessage(RequestOrderDTO requestOrderDTO){
        kafkaTemplate.send("newOrder", requestOrderDTO);
        LOGGER.info("Broker sent message -> %s".formatted(requestOrderDTO.toString()));
    }
}
