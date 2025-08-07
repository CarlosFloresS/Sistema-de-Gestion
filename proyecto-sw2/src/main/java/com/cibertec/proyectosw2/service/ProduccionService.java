package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.ProduccionDto;

import java.util.List;

public interface ProduccionService {
    ProduccionDto crear(ProduccionDto dto);
    List<ProduccionDto> listar();
    ProduccionDto buscar(Long id);
}
