package com.foroChallenge.demo.controller;

import com.foroChallenge.demo.logicaYentidades.cursos.*;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private RepositoryCurso cursoRespository;

    @PostMapping
    public ResponseEntity<ListadoCurso> agregarCurso(@RequestBody @Valid RegistroCurso datosRegistroCurso, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datosRegistroCurso);
        Curso curso = cursoRespository.save(new Curso(datosRegistroCurso));
        ListadoCurso datosListadoCurso = new ListadoCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        URI url = uriComponentsBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoCurso);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoCurso>> mostrarCursos(@PageableDefault(size = 10) Pageable pageable) {
        Page<ListadoCurso> listadoCursos = cursoRespository.findAll(pageable).map(ListadoCurso::new);
        return ResponseEntity.ok(listadoCursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadoCurso> muestraCurso(@PathVariable Long id) {
        Curso curso = cursoRespository.getReferenceById(id);
        ListadoCurso datosListadoCurso = new ListadoCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        return ResponseEntity.ok(datosListadoCurso);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListadoCurso> actualizaCurso(@RequestBody @Valid ActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRespository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarCurso(datosActualizarCurso);
        return ResponseEntity.ok(new ListadoCurso(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarCurso(@PathVariable Long id) {
        cursoRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
