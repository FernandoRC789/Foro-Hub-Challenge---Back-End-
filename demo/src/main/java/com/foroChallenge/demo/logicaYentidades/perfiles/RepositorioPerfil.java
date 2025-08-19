package com.foroChallenge.demo.logicaYentidades.perfiles;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface RepositorioPerfil extends JpaRepository<Perfil, Long> {

    Page<Perfil> findAll(Pageable pageable);
}
