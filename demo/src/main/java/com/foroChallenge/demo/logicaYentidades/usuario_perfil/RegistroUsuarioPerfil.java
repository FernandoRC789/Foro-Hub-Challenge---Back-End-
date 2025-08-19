package com.foroChallenge.demo.logicaYentidades.usuario_perfil;

import jakarta.validation.constraints.NotNull;

public record RegistroUsuarioPerfil(
        @NotNull
        Long usuario_id,
        @NotNull
        Long perfil_id
) {
}
