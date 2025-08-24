package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.VentaRequestDto;
import com.cibertec.proyectosw2.dto.VentaResponseDto;
import com.cibertec.proyectosw2.security.CustomUserDetails;
import com.cibertec.proyectosw2.service.VentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@AllArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    /**
     * Registra una nueva venta completa con todos sus ítems en una sola transacción.
     * El cuerpo de la petición debe ser un JSON con una lista de "items".
     *
     * @param ventaDto   El DTO que contiene la lista de ítems a vender.
     * @param currentUser El usuario autenticado que realiza la venta, inyectado por Spring Security.
     * @return Una lista con los DTOs de todas las ventas individuales registradas.
     */
    @PostMapping
    // CAMBIO 1: El tipo de retorno ahora es una lista dentro del ResponseEntity
    public ResponseEntity<List<VentaResponseDto>> registrarVenta(
            @RequestBody @Valid VentaRequestDto ventaDto,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        Long vendedorId = currentUser.getId();

        // CAMBIO 2: La variable ahora es una lista para recibir el resultado del servicio
        List<VentaResponseDto> ventasGuardadas = ventaService.registrarVenta(ventaDto, vendedorId);

        // CAMBIO 3: Devolvemos la lista completa en la respuesta
        return new ResponseEntity<>(ventasGuardadas, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDto> obtenerVentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVentaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDto>> listarTodasLasVentas() {
        return ResponseEntity.ok(ventaService.listarTodasLasVentas());
    }
}