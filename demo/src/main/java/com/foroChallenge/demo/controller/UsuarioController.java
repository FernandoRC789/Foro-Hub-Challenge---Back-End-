package com.foroChallenge.demo.controller;

import com.foroChallenge.demo.logicaYentidades.usuarios.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private RepositoryUsuario usuarioRepository;

    @PostMapping
    public ResponseEntity<ListadoUsuarios> agregarUsuario(@RequestBody @Valid RegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datosRegistroUsuario);
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        ListadoUsuarios datosListadoUsuarios = new ListadoUsuarios(
                usuario.getId(),
                usuario.getNombre()
        );
        URI url = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoUsuarios);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoUsuarios>> mostrarUsuarios(@PageableDefault(size = 10) Pageable pageable) {
        Page<ListadoUsuarios> listadoUsuarios = usuarioRepository.findAll(pageable).map(ListadoUsuarios::new);
        return ResponseEntity.ok(listadoUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadoUsuarios> muestraUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        ListadoUsuarios datosListadoUsuarios = new ListadoUsuarios(
                usuario.getId(),
                usuario.getNombre()
        );
        /*List<String> lista = usuario.getPerfiles().stream().map(Perfil::getNombre).toList();
        System.out.println(lista);  // prueba de código arbitraria para extraer el ó los perfiles desde el usuario  */
        return ResponseEntity.ok(datosListadoUsuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListadoUsuarios> actualizaUsuario(@RequestBody @Valid ActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarUsuario(datosActualizarUsuario);
        return ResponseEntity.ok(new ListadoUsuarios(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
