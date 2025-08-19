package com.foroChallenge.demo.controller;

import com.foroChallenge.demo.logicaYentidades.usuario_perfil.ListadoUsuarioPerfil;
import com.foroChallenge.demo.logicaYentidades.usuario_perfil.RegistroUsuarioPerfil;
import com.foroChallenge.demo.logicaYentidades.usuario_perfil.ServiceUsuarioPerfil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/usuarioperfil")
@SecurityRequirement(name = "bearer-key")
public class UsuarioPerfilController {

    @Autowired
    private ServiceUsuarioPerfil usuarioPerfilService;

    @PostMapping
    public ResponseEntity<ListadoUsuarioPerfil> agregarUsuariosPerfiles(@RequestBody @Valid RegistroUsuarioPerfil datosRegistroUsuarioPerfil) {
        return ResponseEntity.ok(usuarioPerfilService.agregarPefil(datosRegistroUsuarioPerfil));
    }

    @GetMapping
    public ResponseEntity<List<ListadoUsuarioPerfil>> mostrarPerfilesUsuarios() {
        return ResponseEntity.ok(usuarioPerfilService.mostrarUsuarioPerfil());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarUsuarioPerfil(@PathVariable Long id) {
        usuarioPerfilService.borrarUsuarioPerfil(id);
        return ResponseEntity.noContent().build();
    }

}
