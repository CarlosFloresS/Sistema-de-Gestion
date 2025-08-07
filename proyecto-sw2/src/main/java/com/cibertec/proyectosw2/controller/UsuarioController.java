package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.UsuarioCreateDto;
import com.cibertec.proyectosw2.dto.UsuarioDto;
import com.cibertec.proyectosw2.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioDto> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crear(@RequestBody @Valid UsuarioCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PatchMapping("/{id}/estado")
    public UsuarioDto toggleEstado(@PathVariable Long id, @RequestParam boolean activo) {
        return service.cambiarEstado(id, activo);
    }
}