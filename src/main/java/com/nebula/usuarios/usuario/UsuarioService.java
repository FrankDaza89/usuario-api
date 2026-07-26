package com.nebula.usuarios.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Locale;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse registrar(RegistroUsuarioRequest request) {
        String correoNormalizado = request.correo().trim().toLowerCase(Locale.ROOT);
        if (usuarioRepository.existsByCorreoIgnoreCase(correoNormalizado)) {
            throw new CorreoDuplicadoException();
        }

        Usuario usuario = new Usuario(
                request.nombre().trim(),
                correoNormalizado,
                passwordEncoder.encode(request.clave()),
                Instant.now()
        );

        return UsuarioResponse.desde(usuarioRepository.save(usuario));
    }
}
