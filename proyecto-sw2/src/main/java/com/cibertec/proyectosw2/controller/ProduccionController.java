package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.ProduccionDto;
import com.cibertec.proyectosw2.service.ProduccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producciones")
@RequiredArgsConstructor
public class ProduccionController {

    private final ProduccionService service;

    /* Listar todas las producciones */
    @GetMapping
    public List<ProduccionDto> listar() {
        return service.listar();
    }

    /* Detalle por ID */
    @GetMapping("/{id}")
    public ProduccionDto detalle(@PathVariable Long id) {
        return service.buscar(id);
    }

    /* Registrar nueva producción */
    @PostMapping
    public ResponseEntity<ProduccionDto> crear(@RequestBody @Valid ProduccionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }
}
