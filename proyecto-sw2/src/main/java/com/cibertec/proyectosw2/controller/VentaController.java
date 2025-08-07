package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.VentaDto;
import com.cibertec.proyectosw2.service.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService service;

    /* ==== Listar ventas ==== */
    @GetMapping
    public List<VentaDto> listar() {
        return service.listar();
    }

    /* ==== Registrar venta ==== */
    @PostMapping
    public ResponseEntity<VentaDto> crear(@RequestBody @Valid VentaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }
}
