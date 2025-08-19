package com.foroChallenge.demo.logicaYentidades.usuario_perfil;

import com.foroChallenge.demo.logicaYentidades.perfiles.Perfil;
import com.foroChallenge.demo.logicaYentidades.perfiles.RepositorioPerfil;
import com.foroChallenge.demo.logicaYentidades.usuarios.RepositoryUsuario;
import com.foroChallenge.demo.logicaYentidades.usuarios.Usuario;
import com.foroChallenge.demo.util.errores.ErrorDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUsuarioPerfil {

    @Autowired
    private RepositoryUsuario usuarioRepository;

    @Autowired
    private RepositorioPerfil perfilRespository;

    @Autowired
    private RepositoryUsuarioPerfil usuarioPerfilRepository;

    public ListadoUsuarioPerfil agregarPefil(RegistroUsuarioPerfil datosRegistroUsuarioPerfil) {

        Usuario usuario;
        Perfil perfil;

        if (usuarioRepository.findById(datosRegistroUsuarioPerfil.usuario_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el usuario");
        }
        if (perfilRespository.findById(datosRegistroUsuarioPerfil.perfil_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el perfil");
        }

        usuario = usuarioRepository.getReferenceById(datosRegistroUsuarioPerfil.usuario_id());
        perfil = perfilRespository.getReferenceById(datosRegistroUsuarioPerfil.perfil_id());

        UsuarioPerfil usuarioPerfil = new UsuarioPerfil(null, usuario, perfil);

        UsuarioPerfil usuarioPerfil1 = usuarioPerfilRepository.save(usuarioPerfil);

        return new ListadoUsuarioPerfil(usuarioPerfil1);
    }

    public List<ListadoUsuarioPerfil> mostrarUsuarioPerfil() {
        return usuarioPerfilRepository.findAll().stream().map(ListadoUsuarioPerfil::new).toList();
    }

    public void borrarUsuarioPerfil(Long id) {
        usuarioPerfilRepository.deleteById(id);
    }
}
