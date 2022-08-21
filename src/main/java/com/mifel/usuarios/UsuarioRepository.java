package com.mifel.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreIgnoreCase(String nombre);

    List<Usuario> findByPrimerApellidoIgnoreCase(String nombre);

    Usuario findById(long id);
}
