package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.ProductoDto;
import com.cibertec.proyectosw2.entity.Producto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductoMapper {

    public static Producto toEntity(ProductoDto d) {
        return Producto.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .descripcion(d.getDescripcion())
                .precio(d.getPrecio())
                .stock(d.getStock())
                .estado(true)
                .build();
    }

    public static ProductoDto toDto(Producto p) {
        return ProductoDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .build();
    }
}
