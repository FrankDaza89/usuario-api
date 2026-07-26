package com.nebula.usuarios.usuario;

import java.time.Instant;

public record UsuarioResponse(
        String id,
        String nombre,
        String correo,
        Instant creadoEn
) {
    static UsuarioResponse desde(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getCreadoEn()
        );
    }
}
