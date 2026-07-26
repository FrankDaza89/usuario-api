package com.nebula.usuarios.usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    boolean existsByCorreoIgnoreCase(String correo);

    Optional<Usuario> findByCorreoIgnoreCase(String correo);
}
