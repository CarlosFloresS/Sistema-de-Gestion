package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {

    /**
     * Busca todos los movimientos de inventario para un producto específico,
     * ordenados por fecha de forma ascendente para reconstruir el historial.
     *
     * @param productoId El ID del producto.
     * @return Una lista ordenada de los movimientos de inventario.
     */
    List<MovimientoInventario> findByProductoIdOrderByFechaAsc(Long productoId);

    /**
     * Obtiene el último movimiento registrado para un producto.
     * Es útil para obtener rápidamente el stock resultante más reciente.
     *
     * @param productoId El ID del producto.
     * @return Un Optional con el último movimiento de inventario, si existe.
     */
    Optional<MovimientoInventario> findTopByProductoIdOrderByFechaDesc(Long productoId);
}