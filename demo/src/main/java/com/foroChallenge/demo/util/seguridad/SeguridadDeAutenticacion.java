package com.foroChallenge.demo.util.seguridad;

import com.foroChallenge.demo.logicaYentidades.usuarios.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SeguridadDeAutenticacion implements UserDetailsService {

    @Autowired
    private RepositoryUsuario usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }
}
