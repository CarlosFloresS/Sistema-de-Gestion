package com.cibertec.proyectosw2.dto;

import com.cibertec.proyectosw2.entity.enums.Rol;
import lombok.*;

@Data
@Builder
public class UsuarioDto {
    private Long id;
    private String username;
    private Rol rol;
    private Boolean estado;
}