package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.VentaDto;

import java.util.List;

public interface VentaService {
    VentaDto crear(VentaDto dto);
    List<VentaDto> listar();
}
