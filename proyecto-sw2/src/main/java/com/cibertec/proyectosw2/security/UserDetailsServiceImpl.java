package com.cibertec.proyectosw2.security;

import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repo;

    /* ---------- Carga para autenticación ---------- */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // ¡CAMBIO CLAVE! Devolvemos nuestra clase personalizada en lugar de la genérica.
        return new CustomUserDetails(u);
    }

    /* ---------- Utilidad: usuario actualmente logueado ---------- */
    // Este método puede seguir existiendo, pero ya no lo necesitaremos en los controllers.
    // Lo dejamos por si lo usas en otros servicios.
    public Usuario getCurrentUser() throws UsernameNotFoundException {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String username;

        // Ahora el principal siempre será de tipo CustomUserDetails
        if (principal instanceof CustomUserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}