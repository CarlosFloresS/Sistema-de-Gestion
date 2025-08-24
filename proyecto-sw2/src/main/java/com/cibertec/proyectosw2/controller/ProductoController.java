package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.ProductoRequestDto;
import com.cibertec.proyectosw2.dto.ProductoResponseDto;
import com.cibertec.proyectosw2.service.ProductoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> listarProductosActivos() {
        return ResponseEntity.ok(productoService.listarProductosActivos());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProductoResponseDto>> listarTodosLosProductos() {
        return ResponseEntity.ok(productoService.listarTodosLosProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDto> registrarProducto(@RequestBody @Valid ProductoRequestDto dto) {
        ProductoResponseDto productoGuardado = productoService.registrarProducto(dto);
        return new ResponseEntity<>(productoGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> actualizarProducto(@PathVariable Long id, @RequestBody @Valid ProductoRequestDto dto) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarProducto(@PathVariable Long id) {
        productoService.desactivarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/activar")
    public ResponseEntity<Void> activarProducto(@PathVariable Long id) {
        productoService.activarProducto(id);
        return ResponseEntity.ok().build();
    }
}