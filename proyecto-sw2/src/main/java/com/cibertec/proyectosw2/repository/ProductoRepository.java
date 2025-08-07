package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByEstadoTrue();              // solo activos
}
