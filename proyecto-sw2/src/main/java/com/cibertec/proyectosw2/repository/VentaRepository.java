package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    /**
     * Busca todas las ventas realizadas por un vendedor específico.
     *
     * @param vendedorId El ID del usuario vendedor.
     * @return Una lista de ventas asociadas a ese vendedor.
     */
    List<Venta> findByVendedorId(Long vendedorId);

    /**
     * Busca todas las ventas realizadas dentro de un rango de fechas.
     *
     * @param fechaInicio La fecha y hora de inicio del rango.
     * @param fechaFin La fecha y hora de fin del rango.
     * @return Una lista de ventas que ocurrieron en ese período.
     */
    List<Venta> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}