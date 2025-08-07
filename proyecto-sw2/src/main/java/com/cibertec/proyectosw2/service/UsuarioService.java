package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.UsuarioCreateDto;
import com.cibertec.proyectosw2.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    UsuarioDto crear(UsuarioCreateDto dto);
    List<UsuarioDto> listar();
    UsuarioDto cambiarEstado(Long id, boolean activo);
}