package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.ProduccionRequestDto;
import com.cibertec.proyectosw2.dto.ProduccionResponseDto;
import com.cibertec.proyectosw2.security.CustomUserDetails;
import com.cibertec.proyectosw2.service.ProduccionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producciones")
@AllArgsConstructor
public class ProduccionController {

    private final ProduccionService produccionService;

    @PostMapping
    public ResponseEntity<ProduccionResponseDto> registrarProduccion(
            @RequestBody @Valid ProduccionRequestDto dto,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        Long operarioId = currentUser.getId();
        ProduccionResponseDto produccionGuardada = produccionService.registrarProduccion(dto, operarioId);
        return new ResponseEntity<>(produccionGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduccionResponseDto> obtenerProduccionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produccionService.obtenerProduccionPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProduccionResponseDto>> listarTodasLasProducciones() {
        return ResponseEntity.ok(produccionService.listarTodasLasProducciones());
    }
}