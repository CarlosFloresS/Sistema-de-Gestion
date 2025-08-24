package com.cibertec.proyectosw2.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class VentaResponseDto {

    private Long id;
    private Long productoId;
    private String productoNombre; // Dato enriquecido para el frontend
    private Integer cantidad;
    private BigDecimal precioUnitarioAlVender;
    private BigDecimal totalVenta;
    private LocalDateTime fecha;
    private Long vendedorId;
    private String vendedorUsername; // Dato enriquecido
}