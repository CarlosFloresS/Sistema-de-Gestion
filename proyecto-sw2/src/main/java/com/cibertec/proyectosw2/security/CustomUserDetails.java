package com.cibertec.proyectosw2.security;

import com.cibertec.proyectosw2.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;

    // Este es el método que nos permitirá resolver el problema en los controllers
    public Long getId() {
        return usuario.getId();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Adaptado a tu lógica de un solo rol por usuario
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        // Asumiendo que tu entidad Usuario tiene un campo boolean 'estado'
        // Si no lo tienes, puedes devolver 'true' por ahora.
        // return usuario.isEstado();
        return true;
    }
}