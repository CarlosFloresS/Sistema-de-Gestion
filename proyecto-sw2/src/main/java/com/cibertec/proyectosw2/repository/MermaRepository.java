package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Merma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MermaRepository extends JpaRepository<Merma, Long> {
    List<Merma> findByProductoId(Long productoId);
}
