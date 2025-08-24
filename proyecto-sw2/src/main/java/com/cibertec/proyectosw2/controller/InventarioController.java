package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.AjusteInventarioRequestDto;
import com.cibertec.proyectosw2.dto.MovimientoInventarioResponseDto;
import com.cibertec.proyectosw2.security.CustomUserDetails;
import com.cibertec.proyectosw2.service.InventarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@AllArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @GetMapping("/productos/{productoId}/historial")
    public ResponseEntity<List<MovimientoInventarioResponseDto>> getHistorialDeMovimientos(@PathVariable Long productoId) {
        List<MovimientoInventarioResponseDto> historial = inventarioService.getHistorialDeMovimientos(productoId);
        return ResponseEntity.ok(historial);
    }

    @PostMapping("/ajustes")
    public ResponseEntity<MovimientoInventarioResponseDto> registrarAjusteInventario(
            @RequestBody @Valid AjusteInventarioRequestDto dto,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        Long usuarioId = currentUser.getId();
        MovimientoInventarioResponseDto ajusteRealizado = inventarioService.registrarAjusteInventario(dto, usuarioId);
        return ResponseEntity.ok(ajusteRealizado);
    }
}