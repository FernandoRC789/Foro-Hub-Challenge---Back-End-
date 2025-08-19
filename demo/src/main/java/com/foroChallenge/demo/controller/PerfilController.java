package com.foroChallenge.demo.controller;

import com.foroChallenge.demo.logicaYentidades.perfiles.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@ResponseBody
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private RepositorioPerfil perfilRespository;

    @PostMapping
    public ResponseEntity<ListadoPerfil> agregarPerfil(@RequestBody @Valid RegistroPerfil datosRegistroPerfil, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datosRegistroPerfil);
        Perfil perfil = perfilRespository.save(new Perfil(datosRegistroPerfil));
        ListadoPerfil datosListadoPerfil = new ListadoPerfil(
                perfil.getId(),
                perfil.getNombre()
        );
        URI url = uriComponentsBuilder.path("perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoPerfil);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoPerfil>> mostrarPerfil(@PageableDefault(size = 10) Pageable pageable) {
        Page<ListadoPerfil> listadoPerfils = perfilRespository.findAll(pageable).map(ListadoPerfil::new);
        return ResponseEntity.ok(listadoPerfils);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadoPerfil> muestraPerfil(@PathVariable Long id) {
        Perfil perfil = perfilRespository.getReferenceById(id);
        ListadoPerfil datosListadoPerfil = new ListadoPerfil(
                perfil.getId(),
                perfil.getNombre()
        );
        return ResponseEntity.ok(datosListadoPerfil);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListadoPerfil> actualizaPerfil(@RequestBody @Valid ActualizarPerfil datosActualizarPerfil) {
        Perfil perfil = perfilRespository.getReferenceById(datosActualizarPerfil.id());
        perfil.actualizarPerfil(datosActualizarPerfil);
        return ResponseEntity.ok(new ListadoPerfil(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarPerfil(@PathVariable Long id) {
        perfilRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
