package com.cibertec.proyectosw2.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class MermaResponseDto {

    private Long id;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private String motivo;
    private LocalDateTime fecha;
    private Long operarioId;
    private String operarioUsername;
}