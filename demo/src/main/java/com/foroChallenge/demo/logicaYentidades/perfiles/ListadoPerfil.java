package com.foroChallenge.demo.logicaYentidades.perfiles;

public record ListadoPerfil (
    Long id,
    String nombre
) {
    public ListadoPerfil(Perfil perfil) {
            this(perfil.getId(), perfil.getNombre());
        }
    }
