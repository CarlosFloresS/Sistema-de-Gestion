package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Produccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduccionRepository extends JpaRepository<Produccion, Long> {
    List<Produccion> findByProductoId(Long productoId);
}
