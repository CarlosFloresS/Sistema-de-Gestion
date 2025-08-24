package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.VentaRequestDto;
import com.cibertec.proyectosw2.dto.VentaResponseDto;
import java.util.List; // Asegúrate de importar List

public interface VentaService {

    // Cambia el tipo de retorno de VentaResponseDto a List<VentaResponseDto>
    List<VentaResponseDto> registrarVenta(VentaRequestDto ventaDto, Long vendedorId);

    VentaResponseDto obtenerVentaPorId(Long id);

    List<VentaResponseDto> listarTodasLasVentas();
}