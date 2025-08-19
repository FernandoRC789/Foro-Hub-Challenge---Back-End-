package com.foroChallenge.demo.logicaYentidades.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record AutenticacionUsuario(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena
) {
}
