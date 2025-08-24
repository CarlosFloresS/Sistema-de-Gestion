package com.cibertec.proyectosw2.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProduccionResponseDto {

    private Long id;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private BigDecimal costoUnitarioAlProducir;
    private BigDecimal costoTotalProduccion;
    private LocalDateTime fecha;
    private Long operarioId;
    private String operarioUsername;
}