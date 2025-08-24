package com.cibertec.proyectosw2.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductoRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 80, message = "El nombre no puede exceder los 80 caracteres.")
    private String nombre;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres.")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio.")
    @Positive(message = "El precio debe ser un valor positivo.")
    private BigDecimal precio;

    @NotNull(message = "El costo es obligatorio.")
    @PositiveOrZero(message = "El costo debe ser cero o un valor positivo.")
    private BigDecimal costo;

    // El stock inicial se establecerá mediante una operación de inventario, no aquí.
}