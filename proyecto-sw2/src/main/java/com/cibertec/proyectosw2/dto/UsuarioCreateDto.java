package com.cibertec.proyectosw2.dto;

import com.cibertec.proyectosw2.entity.enums.Rol;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioCreateDto {

    @NotBlank @Size(max = 40)
    private String username;

    @NotBlank @Size(min = 8, max = 30)
    private String passwordPlain;

    private Rol rol = Rol.OPERADOR;
}