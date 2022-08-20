package com.mifelusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/nombre/{nom}")
    public List<Usuario> getUser(@Param("nom") String nombre) {
        return usuarioRepository.findByNombreIgnoreCase("Carlos");
    }

    @GetMapping("/publico")
    public String publico() {
        return "Hola!";
    }
}
