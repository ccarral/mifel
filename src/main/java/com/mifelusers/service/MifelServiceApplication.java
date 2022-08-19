package com.mifelusers.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class MifelServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MifelServiceApplication.class, args);
    }
}
