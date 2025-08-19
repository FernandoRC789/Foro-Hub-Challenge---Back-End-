package com.foroChallenge.demo.logicaYentidades.topicos;

import com.foroChallenge.demo.logicaYentidades.cursos.Curso;
import com.foroChallenge.demo.logicaYentidades.cursos.RepositoryCurso;
import com.foroChallenge.demo.logicaYentidades.respuestas.DTOpicoRespuestas;
import com.foroChallenge.demo.logicaYentidades.usuarios.RepositoryUsuario;
import com.foroChallenge.demo.logicaYentidades.usuarios.Usuario;
import com.foroChallenge.demo.util.errores.ErrorDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TopicoService {

    @Autowired
    private RepositoryUsuario usuarioRepository;
    @Autowired
    private RepositoryCurso cursoRespository;
    @Autowired
    private RepositoryTopico topicoRepository;

    public ListadoTopico agregarTopico(RegistroTopico datosRegistroTopico) {
        Curso curso;
        Usuario usuario;

        if (!usuarioRepository.existsById(datosRegistroTopico.usuario_id())) {
            throw new ErrorDeConsulta("No se encontró el usuario");
        }

        if (cursoRespository.findByNombre(datosRegistroTopico.nombreCurso()).isPresent()) {
            curso = cursoRespository.findByNombre(datosRegistroTopico.nombreCurso()).get();
        } else {
            throw new ErrorDeConsulta("No se encontró el curso");
        }

        usuario = usuarioRepository.findById(datosRegistroTopico.usuario_id()).get();

        Topico topico = new Topico(
                null,
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                LocalDateTime.now(),
                true,
                usuario,
                curso,
                new ArrayList<>()
        );

        Topico topicoR = topicoRepository.save(topico);
        return new ListadoTopico(
                topicoR.getId(),
                topicoR.getTitulo(),
                topicoR.getMensaje(),
                topicoR.getFecha()
        );
    }

    public Page<ListadoTopico> getTopicos(Pageable pageable) {
        return topicoRepository.findAllByStatusTrue(pageable).map(ListadoTopico::new);
    }

    public ListadoTopico getTopico(Long id) {
        if (topicoRepository.findByStatusById(id) == null) {
            throw new ErrorDeConsulta("No se halló el tópico");
        }
        if (!topicoRepository.findByStatusById(id)) {
            throw new ErrorDeConsulta("No se halló el tópico");
        }
        if (topicoRepository.findById(id).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el tópico");
        }
        Topico topicoR = topicoRepository.findById(id).get();

        return new ListadoTopico(
                topicoR.getId(),
                topicoR.getTitulo(),
                topicoR.getMensaje(),
                topicoR.getFecha()
        );
    }

    @Transactional
    public ListadoTopico actualizaTopico(ActualizarTopico datosActualizarTopico) {
        if (!topicoRepository.existsById(datosActualizarTopico.id())) {
            throw new ErrorDeConsulta("No se halló el topico");
        }
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico);
        return new ListadoTopico(topico);
    }

    @Transactional
    public void desactivaTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ErrorDeConsulta("No se halló el topico");
        }
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivaTopico();
    }

    public Page<DTOpicoRespuestas> getRespuestas(Long id, Pageable pageable) {
        if (!topicoRepository.existsById(id) || !topicoRepository.findByStatusById(id)) {
            throw new ErrorDeConsulta("No se halló el topico");
        }
        Page<DTOpicoRespuestas> dtOpicoRespuestas = topicoRepository.findAllByRespuestas(id, pageable).map(DTOpicoRespuestas::new);
        return dtOpicoRespuestas;
    }
}
