package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.AjusteInventarioRequestDto;
import com.cibertec.proyectosw2.dto.MovimientoInventarioResponseDto;
import com.cibertec.proyectosw2.entity.*;
import java.util.List;

public interface InventarioService {
    // Métodos internos para ser usados por otros servicios
    void registrarMovimientoDesdeVenta(Venta venta);
    void registrarMovimientoDesdeProduccion(Produccion produccion);
    void registrarMovimientoDesdeMerma(Merma merma);

    // Método público para ajustes manuales
    MovimientoInventarioResponseDto registrarAjusteInventario(AjusteInventarioRequestDto ajusteDto, Long usuarioId);

    // Métodos de consulta
    int getStockActual(Long productoId);
    List<MovimientoInventarioResponseDto> getHistorialDeMovimientos(Long productoId);
}