package com.cibertec.proyectosw2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO que representa un ítem individual dentro de una solicitud de venta.
 */
@Data
public class VentaItemRequestDto {

    @NotNull(message = "El ID del producto es obligatorio.")
    @Positive(message = "El ID del producto debe ser un número positivo.")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria.")
    @Positive(message = "La cantidad debe ser un número positivo.")
    private Integer cantidad;
}