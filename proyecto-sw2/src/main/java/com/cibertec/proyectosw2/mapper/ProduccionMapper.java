package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.ProduccionRequestDto;
import com.cibertec.proyectosw2.dto.ProduccionResponseDto;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.entity.Produccion;
import com.cibertec.proyectosw2.entity.Usuario;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ProduccionMapper {

    /**
     * Convierte un ProduccionRequestDto a una entidad Produccion.
     * Requiere los objetos 'Producto' y 'Usuario' que se obtienen en el servicio.
     *
     * @param dto El DTO de entrada.
     * @param producto El producto que se está fabricando.
     * @param operario El usuario que registra la producción.
     * @return Una entidad Produccion lista para ser guardada.
     */
    public Produccion toEntity(ProduccionRequestDto dto, Producto producto, Usuario operario) {
        BigDecimal costoTotal = producto.getCosto().multiply(new BigDecimal(dto.getCantidad()));

        return Produccion.builder()
                .producto(producto)
                .cantidad(dto.getCantidad())
                .costoUnitarioAlProducir(producto.getCosto()) // Se congela el costo
                .costoTotalProduccion(costoTotal)
                .fecha(LocalDateTime.now())
                .operario(operario)
                .build();
    }

    /**
     * Convierte una entidad Produccion a un ProduccionResponseDto.
     *
     * @param entity La entidad Produccion desde la base de datos.
     * @return Un DTO con datos enriquecidos.
     */
    public ProduccionResponseDto toResponseDto(Produccion entity) {
        return ProduccionResponseDto.builder()
                .id(entity.getId())
                .productoId(entity.getProducto().getId())
                .productoNombre(entity.getProducto().getNombre())
                .cantidad(entity.getCantidad())
                .costoUnitarioAlProducir(entity.getCostoUnitarioAlProducir())
                .costoTotalProduccion(entity.getCostoTotalProduccion())
                .fecha(entity.getFecha())
                .operarioId(entity.getOperario().getId())
                .operarioUsername(entity.getOperario().getUsername())
                .build();
    }
}