package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.MermaDto;
import com.cibertec.proyectosw2.entity.*;
import com.cibertec.proyectosw2.mapper.MermaMapper;
import com.cibertec.proyectosw2.repository.MermaRepository;
import com.cibertec.proyectosw2.service.MermaService;
import com.cibertec.proyectosw2.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MermaServiceImpl implements MermaService {

    private final MermaRepository mermaRepo;
    private final ProductoService productoService;
    private final com.cibertec.proyectosw2.security.UserDetailsServiceImpl userDetailsService;

    @Override
    public MermaDto crear(MermaDto dto) {
        Producto producto = productoService.obtenerEntidad(dto.getProductoId());
        Usuario  operario  = userDetailsService.getCurrentUser();

        Merma saved = mermaRepo.save(
                MermaMapper.toEntity(dto, producto, operario)
        );

        /* Descuenta del stock */
        producto.setStock(producto.getStock() - dto.getCantidad());

        return MermaMapper.toDto(saved);
    }

    @Override
    public List<MermaDto> listar() {
        return mermaRepo.findAll()
                .stream()
                .map(MermaMapper::toDto)
                .toList();
    }
}
