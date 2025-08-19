package com.foroChallenge.demo.logicaYentidades.usuario_perfil;

public record ListadoUsuarioPerfil(
        String nomrbreUsuario,
        String nombrePerfil
) {

    public ListadoUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
        this(usuarioPerfil.getUsuario().getNombre(), usuarioPerfil.getPerfil().getNombre());
    }
}
