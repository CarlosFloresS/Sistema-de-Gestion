package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.VentaDto;
import com.cibertec.proyectosw2.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class VentaMapper {

    public static Venta toEntity(VentaDto d, Producto producto, Usuario vendedor) {
        return Venta.builder()
                .id(d.getId())
                .producto(producto)
                .cantidad(d.getCantidad())
                .total(d.getTotal())          // asignado en el servicio
                .fecha(LocalDateTime.now())
                .vendedor(vendedor)
                .build();
    }

    public static VentaDto toDto(Venta v) {
        return VentaDto.builder()
                .id(v.getId())
                .productoId(v.getProducto().getId())
                .cantidad(v.getCantidad())
                .total(v.getTotal())
                .fecha(v.getFecha())
                .build();
    }
}
