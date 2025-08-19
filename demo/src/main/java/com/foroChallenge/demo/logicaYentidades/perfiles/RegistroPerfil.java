package com.foroChallenge.demo.logicaYentidades.perfiles;

import jakarta.validation.constraints.NotBlank;

public record RegistroPerfil (
        @NotBlank
        String nombre
){
}
