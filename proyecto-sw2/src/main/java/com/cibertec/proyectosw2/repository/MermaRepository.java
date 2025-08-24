package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Merma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MermaRepository extends JpaRepository<Merma, Long> {

    /**
     * Busca todas las mermas registradas por un operario específico.
     *
     * @param operarioId El ID del usuario operario.
     * @return Una lista de mermas asociadas a ese operario.
     */
    List<Merma> findByOperarioId(Long operarioId);

    /**
     * Busca todas las mermas registradas dentro de un rango de fechas.
     *
     * @param fechaInicio La fecha y hora de inicio del rango.
     * @param fechaFin La fecha y hora de fin del rango.
     * @return Una lista de mermas que ocurrieron en ese período.
     */
    List<Merma> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}