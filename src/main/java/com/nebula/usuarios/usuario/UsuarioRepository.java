package com.nebula.usuarios.usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    boolean existsByCorreoIgnoreCase(String correo);
}
