package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.MermaDto;
import com.cibertec.proyectosw2.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MermaMapper {

    public static Merma toEntity(MermaDto d, Producto producto, Usuario operario) {
        return Merma.builder()
                .id(d.getId())
                .producto(producto)
                .cantidad(d.getCantidad())
                .motivo(d.getMotivo())
                .fecha(LocalDateTime.now())
                .operario(operario)
                .build();
    }

    public static MermaDto toDto(Merma m) {
        return MermaDto.builder()
                .id(m.getId())
                .productoId(m.getProducto().getId())
                .cantidad(m.getCantidad())
                .motivo(m.getMotivo())
                .fecha(m.getFecha())
                .build();
    }
}
