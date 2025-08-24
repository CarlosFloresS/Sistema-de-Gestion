package com.cibertec.proyectosw2.controller;

import com.cibertec.proyectosw2.dto.MermaRequestDto;
import com.cibertec.proyectosw2.dto.MermaResponseDto;
import com.cibertec.proyectosw2.security.CustomUserDetails;
import com.cibertec.proyectosw2.service.MermaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mermas")
@AllArgsConstructor
public class MermaController {

    private final MermaService mermaService;

    @PostMapping
    public ResponseEntity<MermaResponseDto> registrarMerma(
            @RequestBody @Valid MermaRequestDto dto,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        Long operarioId = currentUser.getId();
        MermaResponseDto mermaGuardada = mermaService.registrarMerma(dto, operarioId);
        return new ResponseEntity<>(mermaGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MermaResponseDto> obtenerMermaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mermaService.obtenerMermaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MermaResponseDto>> listarTodasLasMermas() {
        return ResponseEntity.ok(mermaService.listarTodasLasMermas());
    }
}