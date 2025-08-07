package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.VentaDto;
import com.cibertec.proyectosw2.entity.*;
import com.cibertec.proyectosw2.mapper.VentaMapper;
import com.cibertec.proyectosw2.repository.VentaRepository;
import com.cibertec.proyectosw2.service.ProductoService;
import com.cibertec.proyectosw2.service.VentaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepo;
    private final ProductoService productoService;
    private final com.cibertec.proyectosw2.security.UserDetailsServiceImpl userDetailsService;

    @Override
    public VentaDto crear(VentaDto dto) {
        /* 1. Recuperar producto y verificar stock */
        Producto producto = productoService.obtenerEntidad(dto.getProductoId());
        if (producto.getStock() < dto.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente");
        }

        /* 2. Calcular total = precio × cantidad */
        BigDecimal total = producto.getPrecio()
                .multiply(BigDecimal.valueOf(dto.getCantidad()));
        dto.setTotal(total);

        /* 3. Usuario vendedor */
        Usuario vendedor = userDetailsService.getCurrentUser();

        /* 4. Persistir venta */
        Venta saved = ventaRepo.save(
                VentaMapper.toEntity(dto, producto, vendedor)
        );

        /* 5. Descontar stock */
        producto.setStock(producto.getStock() - dto.getCantidad());

        return VentaMapper.toDto(saved);
    }

    @Override
    public List<VentaDto> listar() {
        return ventaRepo.findAll()
                .stream()
                .map(VentaMapper::toDto)
                .toList();
    }
}
