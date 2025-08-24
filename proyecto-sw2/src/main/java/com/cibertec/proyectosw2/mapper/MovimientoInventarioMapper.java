package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.MovimientoInventarioResponseDto;
import com.cibertec.proyectosw2.entity.*;
import com.cibertec.proyectosw2.entity.enums.TipoMovimiento;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class MovimientoInventarioMapper {

    // No hay un método toEntity(RequestDto) porque los movimientos son consecuencia de otras acciones.

    /**
     * Crea una entidad MovimientoInventario a partir de una Produccion.
     *
     * @param produccion La entidad Produccion recién creada.
     * @param stockResultante El nuevo stock del producto después de esta operación.
     * @return Una entidad MovimientoInventario lista para ser guardada.
     */
    public MovimientoInventario toEntityFromProduccion(Produccion produccion, int stockResultante) {
        return MovimientoInventario.builder()
                .producto(produccion.getProducto())
                .cantidad(produccion.getCantidad()) // Positivo
                .tipo(TipoMovimiento.PRODUCCION)
                .origenId(produccion.getId())
                .stockResultante(stockResultante)
                .fecha(produccion.getFecha())
                .build();
    }

    /**
     * Crea una entidad MovimientoInventario a partir de una Venta.
     *
     * @param venta La entidad Venta recién creada.
     * @param stockResultante El nuevo stock del producto después de esta operación.
     * @return Una entidad MovimientoInventario lista para ser guardada.
     */
    public MovimientoInventario toEntityFromVenta(Venta venta, int stockResultante) {
        return MovimientoInventario.builder()
                .producto(venta.getProducto())
                .cantidad(-venta.getCantidad()) // Negativo
                .tipo(TipoMovimiento.VENTA)
                .origenId(venta.getId())
                .stockResultante(stockResultante)
                .fecha(venta.getFecha())
                .build();
    }

    /**
     * Crea una entidad MovimientoInventario a partir de una Merma.
     *
     * @param merma La entidad Merma recién creada.
     * @param stockResultante El nuevo stock del producto después de esta operación.
     * @return Una entidad MovimientoInventario lista para ser guardada.
     */
    public MovimientoInventario toEntityFromMerma(Merma merma, int stockResultante) {
        return MovimientoInventario.builder()
                .producto(merma.getProducto())
                .cantidad(-merma.getCantidad()) // Negativo
                .tipo(TipoMovimiento.MERMA)
                .origenId(merma.getId())
                .stockResultante(stockResultante)
                .fecha(merma.getFecha())
                .build();
    }


    /**
     * Convierte una entidad MovimientoInventario a un MovimientoInventarioResponseDto.
     *
     * @param entity La entidad MovimientoInventario desde la base de datos.
     * @return Un DTO para la respuesta.
     */
    public MovimientoInventarioResponseDto toResponseDto(MovimientoInventario entity) {
        return MovimientoInventarioResponseDto.builder()
                .id(entity.getId())
                .productoId(entity.getProducto().getId())
                .productoNombre(entity.getProducto().getNombre())
                .cantidad(entity.getCantidad())
                .tipo(entity.getTipo())
                .origenId(entity.getOrigenId())
                .stockResultante(entity.getStockResultante())
                .fecha(entity.getFecha())
                .motivo(entity.getMotivo())
                .build();
    }
}