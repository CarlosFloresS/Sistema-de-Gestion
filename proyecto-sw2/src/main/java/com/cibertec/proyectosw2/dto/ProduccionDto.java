package com.cibertec.proyectosw2.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ProduccionDto {

    private Long id;

    @Positive
    private Long productoId;

    @Positive
    private Integer cantidad;

    private LocalDateTime fecha;
}
