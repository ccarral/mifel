package com.mifel.service;

import com.mifel.service.pokemon.Pokemon;
import com.mifel.service.usuarios.Usuario;
import com.mifel.service.usuarios.UsuarioQueryResponse;
import com.mifel.service.usuarios.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/mifel")
public class MifelServiceController {

    @Autowired
    RestTemplate restTemplate;
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
    public Pokemon getPokemon(@PathVariable("nombre") String nombre){
        String url = String.format("https://pokeapi.co/api/v2/pokemon/%s",nombre);
        return this.restTemplate.getForObject(url, Pokemon.class);
    }
}
