package com.nebula.usuarios.comun;

import com.nebula.usuarios.usuario.CorreoDuplicadoException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({CorreoDuplicadoException.class, DuplicateKeyException.class})
    ResponseEntity<ApiError> manejarCorreoDuplicado(RuntimeException exception) {
        return respuesta(HttpStatus.CONFLICT, "Ya existe una cuenta con este correo", Map.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> manejarValidacion(MethodArgumentNotValidException exception) {
        Map<String, String> errores = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errores.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return respuesta(HttpStatus.BAD_REQUEST, "Los datos enviados no son válidos", errores);
    }

    private ResponseEntity<ApiError> respuesta(
            HttpStatus estado,
            String mensaje,
            Map<String, String> errores
    ) {
        return ResponseEntity.status(estado)
                .body(new ApiError(estado.value(), mensaje, Instant.now(), errores));
    }
}
