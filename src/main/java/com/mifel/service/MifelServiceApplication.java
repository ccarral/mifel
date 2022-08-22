package com.mifel.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;

@SpringBootApplication
public class MifelServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MifelServiceApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }

}
