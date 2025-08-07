package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.AuthRequestDto;
import com.cibertec.proyectosw2.dto.AuthResponseDto;
import com.cibertec.proyectosw2.entity.Rol;
import com.cibertec.proyectosw2.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/init-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public void crearAdminInicial() {
        authService.crearUsuario("admin", "admin123", Rol.ADMIN);
    }
}
