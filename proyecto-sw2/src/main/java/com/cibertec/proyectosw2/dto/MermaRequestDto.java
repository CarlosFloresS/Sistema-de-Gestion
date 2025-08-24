package com.cibertec.proyectosw2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MermaRequestDto {

    @NotNull(message = "El ID del producto es obligatorio.")
    @Positive(message = "El ID del producto debe ser un número positivo.")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria.")
    @Positive(message = "La cantidad debe ser un número positivo.")
    private Integer cantidad;

    @NotBlank(message = "El motivo no puede estar vacío.")
    @Size(max = 255, message = "El motivo no puede exceder los 255 caracteres.")
    private String motivo;
}