package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.MermaDto;

import java.util.List;

public interface MermaService {
    MermaDto crear(MermaDto dto);
    List<MermaDto> listar();
}
