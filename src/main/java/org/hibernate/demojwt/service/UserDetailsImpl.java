package org.hibernate.demojwt.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.demojwt.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementación de UserDetails que adapta la entidad Usuario al modelo de seguridad de Spring.
 */
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private final Usuario usuario;

    /**
     * Devuelve las autoridades del usuario (roles/permisos).
     * Actualmente vacío, pero preparado para futuras extensiones.
     *
     * @return colección de GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Puedes mapear roles aquí si los agregas
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return contraseña
     */
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    /**
     * Devuelve el nombre de usuario (email en este caso).
     *
     * @return email
     */
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    /**
     * Indica si la cuenta está expirada.
     *
     * @return true si no está expirada
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta está bloqueada.
     *
     * @return true si no está bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales están expiradas.
     *
     * @return true si no están expiradas
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta está habilitada.
     *
     * @return true si está habilitada
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Devuelve el nombre del usuario.
     *
     * @return nombre
     */
    public String getNombre() {
        return usuario.getNombre();
    }
}
