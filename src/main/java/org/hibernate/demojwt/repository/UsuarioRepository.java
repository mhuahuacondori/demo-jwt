package org.hibernate.demojwt.repository;

import org.hibernate.demojwt.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findOneByEmail(String correo);
}
