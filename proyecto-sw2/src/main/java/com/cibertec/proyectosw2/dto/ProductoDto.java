package com.cibertec.proyectosw2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ProductoDto {

    private Long id;

    @NotBlank
    @Size(max = 80)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    @Positive
    private BigDecimal precio;

    @PositiveOrZero
    private Integer stock;
}
