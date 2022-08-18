package com.mifelusers.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/user")
    public Usuario getUser() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");
        usuario.setPrimerApellido("Carral");
        usuario.setEmail("carloscarral13@gmail.com");
        usuario.setId(0);
        return usuario;
    }
}
