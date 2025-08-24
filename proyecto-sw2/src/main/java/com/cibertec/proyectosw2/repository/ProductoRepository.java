package com.cibertec.proyectosw2.repository;

import com.cibertec.proyectosw2.entity.Producto;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca un producto por su nombre exacto, ignorando mayúsculas y minúsculas.
     * Útil para validar que no se creen productos con nombres duplicados.
     *
     * @param nombre El nombre del producto a buscar.
     * @return Un Optional que contiene el Producto si se encuentra.
     */
    Optional<Producto> findByNombreIgnoreCase(String nombre);

    /**
     * Busca todos los productos cuyo estado sea el especificado.
     * Útil para obtener solo los productos activos.
     *
     * @param estado El estado (true para activo, false para inactivo).
     * @return Una lista de productos que coinciden con el estado.
     */
    List<Producto> findByEstado(boolean estado);

    /**
     * Busca un producto por su ID y aplica un bloqueo de escritura pesimista.
     * Esto previene condiciones de carrera y deadlocks al actualizar el stock.
     * La base de datos bloqueará la fila del producto hasta que la transacción finalice.
     *
     * @param id El ID del producto a buscar y bloquear.
     * @return Un Optional que contiene el Producto si se encuentra.
     */
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Producto> findById(Long id);

}