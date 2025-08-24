package com.cibertec.proyectosw2.dto;

import com.cibertec.proyectosw2.entity.enums.TipoMovimiento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AjusteInventarioRequestDto {

    @NotNull(message = "El ID del producto es obligatorio.")
    @Positive(message = "El ID del producto debe ser un número positivo.")
    private Long productoId;

    // A diferencia de otros DTOs, aquí la cantidad puede ser positiva o negativa
    // para reflejar el ajuste.
    @NotNull(message = "La cantidad del ajuste es obligatoria.")
    private Integer cantidadAjuste; // Ej: +5 si se encontraron más, -3 si faltaban

    @NotBlank(message = "El motivo del ajuste es obligatorio.")
    @Size(max = 255)
    private String motivo; // Ej: "Corrección por conteo físico anual"
}