package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.MermaRequestDto;
import com.cibertec.proyectosw2.dto.MermaResponseDto;
import com.cibertec.proyectosw2.entity.Merma;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.entity.Usuario;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class MermaMapper {

    /**
     * Convierte un MermaRequestDto a una entidad Merma.
     *
     * @param dto El DTO de entrada.
     * @param producto El producto afectado por la merma.
     * @param operario El usuario que registra la merma.
     * @return Una entidad Merma lista para ser guardada.
     */
    public Merma toEntity(MermaRequestDto dto, Producto producto, Usuario operario) {
        return Merma.builder()
                .producto(producto)
                .cantidad(dto.getCantidad())
                .motivo(dto.getMotivo())
                .fecha(LocalDateTime.now())
                .operario(operario)
                .build();
    }

    /**
     * Convierte una entidad Merma a un MermaResponseDto.
     *
     * @param entity La entidad Merma desde la base de datos.
     * @return Un DTO con datos enriquecidos.
     */
    public MermaResponseDto toResponseDto(Merma entity) {
        return MermaResponseDto.builder()
                .id(entity.getId())
                .productoId(entity.getProducto().getId())
                .productoNombre(entity.getProducto().getNombre())
                .cantidad(entity.getCantidad())
                .motivo(entity.getMotivo())
                .fecha(entity.getFecha())
                .operarioId(entity.getOperario().getId())
                .operarioUsername(entity.getOperario().getUsername())
                .build();
    }
}