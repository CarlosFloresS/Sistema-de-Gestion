package com.cibertec.proyectosw2.security;

import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repo;

    /* ---------- Carga para autenticación ---------- */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(
                u.getUsername(),
                u.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + u.getRol()))
        );
    }

    /* ---------- Utilidad: usuario actualmente logueado ---------- */
    public Usuario getCurrentUser() throws UsernameNotFoundException {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username;

        // Cuando el token es válido, principal será un UserDetails
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            // En caso extremo, principal puede ser un String con el username
            username = principal.toString();
        }

        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
