package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.ProduccionRequestDto;
import com.cibertec.proyectosw2.dto.ProduccionResponseDto;
import java.util.List;

public interface ProduccionService {
    ProduccionResponseDto registrarProduccion(ProduccionRequestDto produccionDto, Long operarioId);
    ProduccionResponseDto obtenerProduccionPorId(Long id);
    List<ProduccionResponseDto> listarTodasLasProducciones();
}