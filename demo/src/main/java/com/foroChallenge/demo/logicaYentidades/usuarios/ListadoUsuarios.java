package com.foroChallenge.demo.logicaYentidades.usuarios;

public record ListadoUsuarios(
        Long id,
        String nombre
) {

    public ListadoUsuarios(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre());
    }
}
