package com.foroChallenge.demo.logicaYentidades.cursos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record RegistroCurso(
        @NotBlank
        String nombre,
        @Nullable
        String categoria
) {
}
