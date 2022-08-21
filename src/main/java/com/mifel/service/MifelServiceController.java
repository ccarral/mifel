package com.mifel.service;

import com.mifel.service.usuarios.Usuario;
import com.mifel.service.usuarios.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mifel")
public class MifelServiceController {

    Logger logger = LoggerFactory.getLogger(MifelServiceController.class);

    @Autowired
    UsuarioRepository usuarioRepository;


    /**
     * Regresa todos los usuarios cuyo nombre coincide con `nombre`
     *
     * @param nombre Nombre indistinto de capitalización
     * @return
     */
    @GetMapping("/usuarios/nombre/{nombre}")
    public List<Usuario> getUsuariosByNombre(@PathVariable("nombre") String nombre) {
        logger.info("Usuarios por nombre [" + nombre + "]");
        return usuarioRepository.findByNombreIgnoreCase(nombre);
    }

    /**
     * @return Regresa todos los usuarios contenidos en la B.D
     */
    @GetMapping("/usuarios/")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
    @GetMapping("/usuarios/id/{id}")
    public Usuario getAllUsuarios(@PathVariable("id") long id) {
        return usuarioRepository.findById(id);
    }
}
