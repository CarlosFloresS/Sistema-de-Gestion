package com.cibertec.proyectosw2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class MermaDto {

    private Long id;

    @Positive
    private Long productoId;

    @Positive
    private Integer cantidad;

    @Size(max = 255)
    private String motivo;

    private LocalDateTime fecha;
}
