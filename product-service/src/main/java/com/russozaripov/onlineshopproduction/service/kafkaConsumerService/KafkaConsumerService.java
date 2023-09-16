package com.russozaripov.onlineshopproduction.service.kafkaConsumerService;

import com.russozaripov.onlineshopproduction.entity.Product;
import com.russozaripov.onlineshopproduction.repository.productRepository.ProductRepository;
import com.russozaripov.onlineshopproduction.service.productService.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
    @KafkaListener(topics = "inventory-topic", groupId = "myGroup")
    public void get_Message_From_Inventory(String message) throws Exception {
        LOGGER.info("Message received -> %s".formatted(message));
        Product product = productRepository.findProductBySkuCode(message).get();
        product.setInStock(true);
        productRepository.save(product);
        LOGGER.info("Is in stock: true -> %s".formatted(message));
        productService.update_Cache_With_AllProducts();
    }
}
