package com.mifelusers.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void findByNombreIgnoreCase() {
        // TODO: Agregar datos de prueba en un data.sql separado
        List<Usuario> resultados = usuarioRepository.findByNombreIgnoreCase("carlos");
        assertEquals(2, resultados.size());
        resultados = usuarioRepository.findByNombreIgnoreCase("ignacio");
        assertEquals(0, resultados.size());
    }

    @Test
    void findByPrimerApellidoIgnoreCase() {
        List<Usuario> resultados = usuarioRepository.findByPrimerApellidoIgnoreCase("ELIZONDO");
        assertEquals(1, resultados.size());
    }

    @Test
    void findById() {
        Usuario usuario = usuarioRepository.findById(0);
        assertEquals("Carlos", usuario.getNombre());
        assertEquals("Carral", usuario.getPrimerApellido());
    }
}