package com.mifelusers.service;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOAuth2Login;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class MifelServiceApplicationTests {


    @Autowired
    MockMvc mockMvc;

    /**
     * Prueba que una solicitud no autenticada regresa Unauthorized
     *
     * @throws Exception
     */
    @Test
    void redirectNonAuth() throws Exception {
        this.mockMvc.perform(get("/api/mifel/usuarios/"))
                .andExpect(status().isFound());
    }

    /**
     * Valida que un usuario autenticado pueda acceder a los usuarios
     *
     * @throws Exception
     */
    @Test
    void accessUsuarioAuth() throws Exception {
        this.mockMvc.perform(get("/api/mifel/usuarios/")
                        .with(oauth2Login()))
                .andExpect(status().isOk());
    }


}
