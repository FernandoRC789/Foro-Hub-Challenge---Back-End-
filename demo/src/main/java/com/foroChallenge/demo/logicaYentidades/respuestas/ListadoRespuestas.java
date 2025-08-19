package com.foroChallenge.demo.logicaYentidades.respuestas;

import java.time.LocalDateTime;

public record ListadoRespuestas(
        Long id,
        String mensaje,
        String nombreTopico,
        LocalDateTime fecha,
        String nombreUsuario,
        Boolean solucion
) {

    public ListadoRespuestas(Respuesta respuesta) {
        this(respuesta.getId() ,respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getFecha(), respuesta.getUsuario().getNombre(), respuesta.getSolucion());
    }
}
