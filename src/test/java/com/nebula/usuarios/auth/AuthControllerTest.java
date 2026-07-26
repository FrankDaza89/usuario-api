package com.nebula.usuarios.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    void iniciaSesionConCredencialesValidas() throws Exception {
        LoginRequest request = new LoginRequest("admin@example.com", "admin123");
        when(authService.iniciarSesion(any())).thenReturn(
                new LoginResponse("usuario-id", "Admin", "admin@example.com", "Inicio de sesión exitoso")
        );

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("admin@example.com"))
                .andExpect(jsonPath("$.mensaje").value("Inicio de sesión exitoso"))
                .andExpect(jsonPath("$.clave").doesNotExist());
    }

    @Test
    void rechazaCredencialesInvalidas() throws Exception {
        when(authService.iniciarSesion(any())).thenThrow(new CredencialesInvalidasException());

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new LoginRequest("admin@example.com", "incorrecta")
                        )))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensaje").value("Correo o contraseña incorrectos"));
    }
}
