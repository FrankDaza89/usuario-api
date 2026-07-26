package com.nebula.usuarios.auth;

public record LoginResponse(
        String id,
        String nombre,
        String correo,
        String mensaje
) {
}
