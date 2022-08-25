package com.mifel.service;

import com.mifel.service.crypto.Base64DefaultCipher;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.crypto.KeyGenerator;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class MifelServiceApplicationTests {

    // @Autowired
    // private TestRestTemplate restTemplate;

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

    @Test
    void testPokemonSuccess() throws Exception {
        this.mockMvc.perform(get("/api/mifel/pokemon/pikachu")
                .with(oauth2Login()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['pokemon'].id", is(25)));
    }

    @Test
    void accessAdminAuth() throws Exception {
        this.mockMvc.perform(get("/api/mifel/admin")
                .with(oauth2Login().authorities(AuthorityUtils.createAuthorityList("ROLE_ADMIN"))))
                .andExpect(status().isOk());
    }

    @Test
    void testEncryptApi() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        var secret = keyGenerator.generateKey();
        byte[] keyBytes = secret.getEncoded();
        String base64Key = Base64DefaultCipher.encodeBase64UrlSafe(keyBytes);
        String msg = "ESTO ES UN MENSAJE";
        String url = String.format("/api/mifel/encripta/?msg=%s&key=%s", msg, base64Key);

        this.mockMvc.perform(get(url).with(oauth2Login()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['success']", is(true)));
    }
}
