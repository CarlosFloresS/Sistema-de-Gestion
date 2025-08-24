package com.cibertec.proyectosw2.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductoResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal costo;
    private Integer stock;
    private Boolean estado;
}