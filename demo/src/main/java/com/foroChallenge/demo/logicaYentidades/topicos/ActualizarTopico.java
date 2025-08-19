package com.foroChallenge.demo.logicaYentidades.topicos;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje
) {
}
