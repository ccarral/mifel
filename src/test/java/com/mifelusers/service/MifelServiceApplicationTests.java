package com.mifelusers.service;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class MifelServiceApplicationTests {

    @Autowired
    MifelServiceApplication app;

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(app);
    }

    /**
     * Prueba que una solicitud no autenticada redirige al login
     *
     * @throws Exception
     */
    @Test
    void redirectNonAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

}
