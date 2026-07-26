package com.nebula.usuarios.comun;

import java.time.Instant;
import java.util.Map;

public record ApiError(
        int estado,
        String mensaje,
        Instant fecha,
        Map<String, String> errores
) {
}
