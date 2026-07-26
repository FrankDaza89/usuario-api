package com.nebula.usuarios.auth;

public class CredencialesInvalidasException extends RuntimeException {

    public CredencialesInvalidasException() {
        super("Correo o contraseña incorrectos");
    }
}
