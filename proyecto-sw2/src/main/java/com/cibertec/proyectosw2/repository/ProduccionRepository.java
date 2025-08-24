package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Produccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProduccionRepository extends JpaRepository<Produccion, Long> {

    /**
     * Busca todas las producciones registradas por un operario específico.
     *
     * @param operarioId El ID del usuario operario.
     * @return Una lista de producciones asociadas a ese operario.
     */
    List<Produccion> findByOperarioId(Long operarioId);

    /**
     * Busca todas las producciones realizadas dentro de un rango de fechas.
     *
     * @param fechaInicio La fecha y hora de inicio del rango.
     * @param fechaFin La fecha y hora de fin del rango.
     * @return Una lista de producciones que ocurrieron en ese período.
     */
    List<Produccion> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}