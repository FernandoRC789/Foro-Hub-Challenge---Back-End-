package com.foroChallenge.demo.logicaYentidades.perfiles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarPerfil(
    @NotNull
    Long id,
    @NotBlank
    String nombre){

}
