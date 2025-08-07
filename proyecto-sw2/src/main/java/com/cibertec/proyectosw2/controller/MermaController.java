package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.MermaDto;
import com.cibertec.proyectosw2.service.MermaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mermas")
@RequiredArgsConstructor
public class MermaController {

    private final MermaService service;

    /* Listar todas las mermas */
    @GetMapping
    public List<MermaDto> listar() {
        return service.listar();
    }

    /* Registrar merma */
    @PostMapping
    public ResponseEntity<MermaDto> crear(@RequestBody @Valid MermaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }
}
