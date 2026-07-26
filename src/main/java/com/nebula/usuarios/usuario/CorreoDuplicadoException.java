package com.nebula.usuarios.usuario;

public class CorreoDuplicadoException extends RuntimeException {

    public CorreoDuplicadoException() {
        super("Ya existe una cuenta con este correo");
    }
}
