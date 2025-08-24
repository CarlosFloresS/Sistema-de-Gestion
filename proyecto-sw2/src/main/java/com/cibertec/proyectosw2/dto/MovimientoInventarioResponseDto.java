package com.cibertec.proyectosw2.dto;

import com.cibertec.proyectosw2.entity.enums.TipoMovimiento;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class MovimientoInventarioResponseDto {

    private Long id;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad; // Será + para entradas y - para salidas
    private TipoMovimiento tipo;
    private Long origenId; // ID de la venta/producción/merma que lo causó
    private Integer stockResultante; // El stock que quedó después de este movimiento
    private LocalDateTime fecha;
    private String motivo;
}