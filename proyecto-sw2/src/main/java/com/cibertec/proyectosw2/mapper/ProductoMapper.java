package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.ProductoRequestDto;
import com.cibertec.proyectosw2.dto.ProductoResponseDto;
import com.cibertec.proyectosw2.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    /**
     * Convierte un ProductoRequestDto a una entidad Producto.
     * Ideal para crear un nuevo producto. El stock inicial se asume en 0.
     * La carga inicial de stock debe ser un MovimientoInventario separado.
     *
     * @param dto El DTO con los datos de entrada.
     * @return Una entidad Producto lista para ser guardada.
     */
    public Producto toEntity(ProductoRequestDto dto) {
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .costo(dto.getCosto())
                .stock(0) // El stock inicial siempre es 0. Se carga con un AJUSTE_INICIAL.
                .estado(true) // Por defecto, un nuevo producto está activo.
                .build();
    }

    /**
     * Convierte una entidad Producto a un ProductoResponseDto.
     * Ideal para mostrar los datos de un producto al cliente.
     *
     * @param entity La entidad Producto desde la base de datos.
     * @return Un DTO con los datos para la respuesta.
     */
    public ProductoResponseDto toResponseDto(Producto entity) {
        return ProductoResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .costo(entity.getCosto())
                .stock(entity.getStock())
                .estado(entity.getEstado())
                .build();
    }
}