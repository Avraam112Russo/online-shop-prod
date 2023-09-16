package com.russozaripov.inventoryservice.service.kafkaBroker;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final String TOPIC_1 = "inventory-topic";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;



    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class); //логирование сообщений

    public void sendMessageToKafka(String message){
        LOGGER.info("sent message: %s".formatted(message));
        kafkaTemplate.send(TOPIC_1, message);
    }
}
