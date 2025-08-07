package com.cibertec.proyectosw2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class VentaDto {

    private Long id;

    @Positive
    private Long productoId;

    @Positive
    private Integer cantidad;

    private BigDecimal total;          // se calcula en el servicio

    private LocalDateTime fecha;
}
