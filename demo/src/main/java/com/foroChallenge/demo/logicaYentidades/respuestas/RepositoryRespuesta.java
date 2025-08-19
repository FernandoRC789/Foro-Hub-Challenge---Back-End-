package com.foroChallenge.demo.logicaYentidades.respuestas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRespuesta extends JpaRepository<Respuesta,Long> {
    Page<Respuesta> findAll(Pageable pageable);

}
