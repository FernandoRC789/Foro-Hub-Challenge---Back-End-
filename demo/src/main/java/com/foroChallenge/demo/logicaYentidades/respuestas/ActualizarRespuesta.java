package com.foroChallenge.demo.logicaYentidades.respuestas;

import lombok.NonNull;

public record ActualizarRespuesta(
        @NonNull
        Long id,
        String mensaje,
        Boolean solucion
) {
}
