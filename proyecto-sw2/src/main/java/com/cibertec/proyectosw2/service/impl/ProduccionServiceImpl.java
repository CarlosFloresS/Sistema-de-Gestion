package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.ProduccionDto;
import com.cibertec.proyectosw2.entity.*;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.ProduccionMapper;
import com.cibertec.proyectosw2.repository.ProduccionRepository;
import com.cibertec.proyectosw2.service.ProduccionService;
import com.cibertec.proyectosw2.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduccionServiceImpl implements ProduccionService {

    private final ProduccionRepository produccionRepo;
    private final ProductoService productoService;
    private final com.cibertec.proyectosw2.security.UserDetailsServiceImpl userDetailsService;

    @Override
    public ProduccionDto crear(ProduccionDto dto) {
        Producto producto = productoService.obtenerEntidad(dto.getProductoId());
        Usuario operario  = userDetailsService.getCurrentUser();

        Produccion saved = produccionRepo.save(
                ProduccionMapper.toEntity(dto, producto, operario)
        );

        /* Aumenta stock */
        producto.setStock(producto.getStock() + dto.getCantidad());

        return ProduccionMapper.toDto(saved);
    }

    @Override
    public List<ProduccionDto> listar() {
        return produccionRepo.findAll()
                .stream()
                .map(ProduccionMapper::toDto)
                .toList();
    }

    @Override
    public ProduccionDto buscar(Long id) {
        Produccion p = produccionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produccion", id));
        return ProduccionMapper.toDto(p);
    }
}
