package com.russozaripov.inventoryservice.configuration;

import com.russozaripov.inventoryservice.event.DTO.RequestOrderDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class InventoryConfiguration {
    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("inventory-topic").build();
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, RequestOrderDTO> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, RequestOrderDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE); // Установите MANUAL_IMMEDIATE
//        return factory;
//    }
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Value("${spring.kafka.consumer.group-id}")
//    private String groupId;
//
//    @Bean
//    public ConsumerFactory<String, RequestOrderDTO> consumerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        // Добавьте другие настройки, если необходимо
//
//        ErrorHandlingDeserializer<RequestOrderDTO> errorHandlingDeserializer =
//                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(RequestOrderDTO.class));
//        return new DefaultKafkaConsumerFactory<>(properties,
//                new StringDeserializer(),
//                errorHandlingDeserializer);
//    }
}
