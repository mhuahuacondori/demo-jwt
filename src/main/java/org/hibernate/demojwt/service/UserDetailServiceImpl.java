package org.hibernate.demojwt.service;

import org.hibernate.demojwt.entity.Usuario;
import org.hibernate.demojwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository
                .findOneByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("El usuario con email "+email+" no existe"));
        return new UserDetailsImpl(usuario);
    }
}
