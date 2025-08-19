package com.foroChallenge.demo.logicaYentidades.usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAll(Pageable pageable);

    UserDetails findByEmail(String username);
}
