package com.foroChallenge.demo.logicaYentidades.topicos;

import java.time.LocalDateTime;

public record ListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha
) {

    public ListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha());
    }
}
