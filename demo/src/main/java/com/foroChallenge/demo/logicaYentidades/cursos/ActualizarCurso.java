package com.foroChallenge.demo.logicaYentidades.cursos;

import jakarta.validation.constraints.NotNull;

public record ActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        String categoria
) {
}
