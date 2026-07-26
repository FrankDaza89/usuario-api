package com.nebula.usuarios.auth;

import com.nebula.usuarios.usuario.Usuario;
import com.nebula.usuarios.usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse iniciarSesion(LoginRequest request) {
        String correo = request.correo().trim().toLowerCase(Locale.ROOT);
        Usuario usuario = usuarioRepository.findByCorreoIgnoreCase(correo)
                .orElseThrow(CredencialesInvalidasException::new);

        if (!passwordEncoder.matches(request.clave(), usuario.getClaveHash())) {
            throw new CredencialesInvalidasException();
        }

        return new LoginResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                "Inicio de sesión exitoso"
        );
    }
}
