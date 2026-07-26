package com.nebula.usuarios.usuario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitData {

    @Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                // Crear usuario de prueba para inicializar la BD
                Usuario usuario = new Usuario(
                        "Admin",
                        "admin@example.com",
                        passwordEncoder.encode("admin123"),
                        java.time.Instant.now()
                );
                usuarioRepository.save(usuario);
                System.out.println("BD de usuarios inicializada con usuario de prueba");
            }
        };
    }
}
