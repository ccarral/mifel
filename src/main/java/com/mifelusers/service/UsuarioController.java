package com.mifelusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuario")
    public List<Usuario> getUser() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/publico")
    public String publico() {
        return "Hola!";
    }
}
