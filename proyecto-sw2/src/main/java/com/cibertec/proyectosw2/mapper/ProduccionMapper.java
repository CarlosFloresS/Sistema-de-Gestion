package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.ProduccionDto;
import com.cibertec.proyectosw2.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProduccionMapper {

    public static Produccion toEntity(ProduccionDto d, Producto producto, Usuario operario) {
        return Produccion.builder()
                .id(d.getId())
                .producto(producto)
                .cantidad(d.getCantidad())
                .fecha(LocalDateTime.now())
                .operario(operario)
                .build();
    }

    public static ProduccionDto toDto(Produccion p) {
        return ProduccionDto.builder()
                .id(p.getId())
                .productoId(p.getProducto().getId())
                .cantidad(p.getCantidad())
                .fecha(p.getFecha())
                .build();
    }
}
