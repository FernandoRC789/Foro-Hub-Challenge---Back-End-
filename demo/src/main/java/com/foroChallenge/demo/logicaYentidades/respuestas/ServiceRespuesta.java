package com.foroChallenge.demo.logicaYentidades.respuestas;

import com.foroChallenge.demo.logicaYentidades.topicos.RepositoryTopico;
import com.foroChallenge.demo.logicaYentidades.topicos.Topico;
import com.foroChallenge.demo.logicaYentidades.usuarios.RepositoryUsuario;
import com.foroChallenge.demo.logicaYentidades.usuarios.Usuario;
import com.foroChallenge.demo.util.errores.ErrorDeConsulta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceRespuesta {

    @Autowired
    private RepositoryRespuesta respuestaRepository;
    @Autowired
    private RepositoryTopico topicoRepository;
    @Autowired
    private RepositoryUsuario usuarioRepository;

    public ListadoRespuestas agregarRespuesta(RegistroRespuesta datosRegistroRespuesta) {
        Topico topico;
        Usuario usuario;
        //Validar topico_id e usuario_id -> construir la respuesta y salvar en repositorio.

        if (topicoRepository.findById(datosRegistroRespuesta.topico_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el topico");
        }
        if (!topicoRepository.findByStatusById(datosRegistroRespuesta.topico_id())) {
            throw new ErrorDeConsulta("No se halló el topico");
        }
        if (usuarioRepository.findById(datosRegistroRespuesta.usuario_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el usuario");
        }
        topico = topicoRepository.findById(datosRegistroRespuesta.topico_id()).get();
        usuario = usuarioRepository.findById(datosRegistroRespuesta.usuario_id()).get();

        Respuesta respuestaI = new Respuesta(
                null,
                datosRegistroRespuesta.mensaje(),
                topico,
                LocalDateTime.now(),
                usuario,
                false
        );
        topico.agregarRespuesta(respuestaI);
        Respuesta respuesta = respuestaRepository.save(respuestaI);
        return new ListadoRespuestas(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFecha(),
                respuesta.getUsuario().getNombre(),
                respuesta.getSolucion()
        );

    }

    public Page<ListadoRespuestas> getRespuestas(Pageable pageable) {
        return respuestaRepository.findAll(pageable).map(ListadoRespuestas::new);
    }

    public void deleteRespuesta(Long id) {
        respuestaRepository.deleteById(id);
    }

    public ListadoRespuestas getRespuesta(Long id) {
        if (respuestaRepository.findById(id).isEmpty()) {
            throw new ErrorDeConsulta("No se halló la respuesta");
        }
        Respuesta respuesta = respuestaRepository.findById(id).get();
        return new ListadoRespuestas(respuesta) ;
    }

    @Transactional
    public ListadoRespuestas actualizaRespuesta(ActualizarRespuesta datosActualizarRespuesta) {
        if (respuestaRepository.findById(datosActualizarRespuesta.id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló la respuesta");
        }
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualiza(datosActualizarRespuesta);
        return new ListadoRespuestas(respuesta);
    }
}
