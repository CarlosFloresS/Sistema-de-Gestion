package com.cibertec.proyectosw2.mapper;

import com.cibertec.proyectosw2.dto.UsuarioCreateDto;
import com.cibertec.proyectosw2.dto.UsuarioDto;
import com.cibertec.proyectosw2.entity.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UsuarioMapper {

    /* Convertir DTO→Entidad usando el builder generado por Lombok */
    public static Usuario toEntity(UsuarioCreateDto dto, PasswordEncoder encoder) {
        return Usuario.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPasswordPlain()))   // asegúrate que el campo exista
                .rol(dto.getRol())
                .estado(true)
                .build();
    }

    /* Convertir Entidad→DTO */
    public static UsuarioDto toDto(Usuario u) {
        return UsuarioDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .rol(u.getRol())
                .estado(u.getEstado())
                .build();
    }
}
