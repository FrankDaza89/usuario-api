package com.nebula.usuarios.usuario;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    private String nombre;

    @Indexed(unique = true)
    private String correo;

    @Field("clave_hash")
    private String claveHash;

    @Field("creado_en")
    private Instant creadoEn;

    protected Usuario() {
    }

    public Usuario(String nombre, String correo, String claveHash, Instant creadoEn) {
        this.nombre = nombre;
        this.correo = correo;
        this.claveHash = claveHash;
        this.creadoEn = creadoEn;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }
}
