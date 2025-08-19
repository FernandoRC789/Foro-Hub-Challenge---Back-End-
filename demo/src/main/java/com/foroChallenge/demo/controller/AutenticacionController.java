package com.foroChallenge.demo.controller;


import com.foroChallenge.demo.logicaYentidades.usuarios.AutenticacionUsuario;
import com.foroChallenge.demo.logicaYentidades.usuarios.Usuario;
import com.foroChallenge.demo.util.seguridad.JWTToken;
import com.foroChallenge.demo.util.seguridad.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticacion(@RequestBody @Valid AutenticacionUsuario datosAutenticacionUsuario){
        Authentication autenticacionToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(), datosAutenticacionUsuario.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(autenticacionToken);
        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTToken(jwtToken));
    }
}
