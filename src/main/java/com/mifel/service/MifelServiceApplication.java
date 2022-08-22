package com.mifel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@SpringBootApplication
public class MifelServiceApplication {

    @Value("${secrets.key}")
    String aes128Key;
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

    @Bean
    public SecretKey secretKey(){
            byte[] keyBytes = aes128Key.getBytes(StandardCharsets.US_ASCII);
            return new SecretKeySpec(keyBytes,"AES");
    }

}
