package com.foroChallenge.demo.logicaYentidades.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Long topico_id,
        @NotNull
        Long usuario_id
) {
}
