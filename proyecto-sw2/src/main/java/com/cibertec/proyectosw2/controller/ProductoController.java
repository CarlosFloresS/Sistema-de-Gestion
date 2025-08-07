package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.ProductoDto;
import com.cibertec.proyectosw2.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    /* ===== Listar productos activos ===== */
    @GetMapping
    public List<ProductoDto> listar() {
        return service.listar();
    }

    /* ===== Crear producto ===== */
    @PostMapping
    public ResponseEntity<ProductoDto> crear(@RequestBody @Valid ProductoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    /* ===== Actualizar producto ===== */
    @PutMapping("/{id}")
    public ProductoDto actualizar(@PathVariable Long id, @RequestBody @Valid ProductoDto dto) {
        return service.actualizar(id, dto);
    }

    /* ===== Deshabilitar producto ===== */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
