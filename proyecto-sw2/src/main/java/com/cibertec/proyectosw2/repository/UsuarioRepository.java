package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario (username).
     * Esencial para el proceso de login y para verificar si un username ya existe.
     *
     * @param username El nombre de usuario a buscar.
     * @return Un Optional que contiene el Usuario si se encuentra.
     */
    Optional<Usuario> findByUsername(String username);
}