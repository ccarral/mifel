package com.mifel.service;

import com.mifel.service.crypto.Base64DefaultCipher;
import com.mifel.service.crypto.CryptoResponse;
import com.mifel.service.crypto.DefaultCipher;
import com.mifel.service.pokemon.Pokemon;
import com.mifel.service.pokemon.PokemonQueryResponse;
import com.mifel.service.usuarios.Usuario;
import com.mifel.service.usuarios.UsuarioQueryResponse;
import com.mifel.service.usuarios.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/api/mifel")
public class MifelServiceController {

    @Autowired
    RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(MifelServiceController.class);

    @Autowired
    SecureRandom secureRandom;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SecretKey defaultSecretKey;


    /**
     * Regresa todos los usuarios cuyo nombre coincide con `nombre`
     *
     * @param nombre Nombre indistinto de capitalización
     * @return
     */
    @GetMapping("/usuarios/nombre/{nombre}")
    public UsuarioQueryResponse getUsuariosByNombre(@PathVariable("nombre") String nombre) {
        logger.info("Usuarios por nombre [" + nombre + "]");
        List<Usuario> usuarios =  usuarioRepository.findByNombreIgnoreCase(nombre);
        UsuarioQueryResponse response = new UsuarioQueryResponse();
        response.setUsuarios(usuarios);
        response.setCount(usuarios.size());
        response.setSuccess(usuarios.size() > 0);
        return response;
    }

    /**
     * @return Regresa todos los usuarios contenidos en la B.D
     */
    @GetMapping("/usuarios/")
    public UsuarioQueryResponse getAllUsuarios() {
        UsuarioQueryResponse response = new UsuarioQueryResponse();
        List<Usuario> usuarios =  usuarioRepository.findAll();
        response.setUsuarios(usuarios);
        response.setSuccess(true);
        response.setCount(usuarios.size());
        return response;
    }
    @GetMapping("/usuarios/id/{id}")
    public UsuarioQueryResponse getAllUsuarios(@PathVariable("id") long id) {
        var usuario =  usuarioRepository.findById(id);
        UsuarioQueryResponse response = new UsuarioQueryResponse();
        if(usuario != null){
            response.getUsuarios().add(usuario);
        }
        response.setSuccess(response.getUsuarios().size() > 0);
        response.setCount(response.getUsuarios().size());
        return response;
    }

    @GetMapping("/pokemon/{nombre}")
    public PokemonQueryResponse getPokemon(@PathVariable("nombre") String nombre){
        String url = String.format("https://pokeapi.co/api/v2/pokemon/%s",nombre);
        Pokemon pokemon = null;
        PokemonQueryResponse response = new PokemonQueryResponse();
        try {
            pokemon = this.restTemplate.getForObject(url, Pokemon.class);
            response.setPokemon(pokemon);
            response.setSuccess(true);
        } catch (RestClientException e) {
            // En caso de 500 por parte de la API
            response.setSuccess(false);
        }
        return response;
    }

    @GetMapping("/encripta/")
    public CryptoResponse encryptService(@RequestParam(name = "msg") String msg, @RequestParam(name = "key", required = false) String urlSafeBase64Key){
        logger.info(String.format("Mensaje recibido: %s", msg));
        logger.info(String.format("Clave recibida: %s", urlSafeBase64Key));
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);
        // Se asume que el mensaje esta en UTF 8
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        CryptoResponse res = new CryptoResponse();
        try {
            String base64EncryptedMsg = null;
            if(urlSafeBase64Key != null){
                base64EncryptedMsg = Base64DefaultCipher.encrypt(msgBytes, urlSafeBase64Key, iv);
            }else{
                byte[] encrypted = DefaultCipher.encrypt(msgBytes, defaultSecretKey, iv);
                base64EncryptedMsg = Base64DefaultCipher.encodeBase64(encrypted);
            }
            res.setEncryptedBase64(base64EncryptedMsg);
            res.setSuccess(true);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            res.setFailureReason(e.getMessage());
            res.setSuccess(false);
        }
        return res;
    }
}
