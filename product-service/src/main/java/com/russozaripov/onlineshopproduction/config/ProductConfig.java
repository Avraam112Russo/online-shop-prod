package com.russozaripov.onlineshopproduction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan(basePackages = "com.russozaripov.onlineshopproduction")
public class ProductConfig {
    @Bean
//    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
//    @Primary
//    @Bean
////    @LoadBalanced
//    public RestTemplate loadBalancedRestTemplate() {
//        return new RestTemplate();
//    }
}
