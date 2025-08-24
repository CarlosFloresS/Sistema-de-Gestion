package com.cibertec.proyectosw2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

/**
 * DTO principal para una solicitud de registro de venta.
 * Contiene una lista de todos los productos y sus cantidades que se van a vender.
 */
@Data
public class VentaRequestDto {

    @NotEmpty(message = "La lista de productos no puede estar vacía.")
    @Valid // Esta anotación es crucial para que se validen los campos de cada VentaItemRequestDto.
    private List<VentaItemRequestDto> items;
}