package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.MermaRequestDto;
import com.cibertec.proyectosw2.dto.MermaResponseDto;
import java.util.List;

public interface MermaService {
    MermaResponseDto registrarMerma(MermaRequestDto mermaDto, Long operarioId);
    MermaResponseDto obtenerMermaPorId(Long id);
    List<MermaResponseDto> listarTodasLasMermas();
}