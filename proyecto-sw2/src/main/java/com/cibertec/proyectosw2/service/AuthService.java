package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.AuthRequestDto;
import com.cibertec.proyectosw2.dto.AuthResponseDto;
import com.cibertec.proyectosw2.entity.enums.Rol;

public interface AuthService {
    AuthResponseDto login(AuthRequestDto req);
    void crearUsuario(String username, String password, Rol rol);
}