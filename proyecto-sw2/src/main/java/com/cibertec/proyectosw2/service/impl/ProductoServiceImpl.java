package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.ProductoDto;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.ProductoMapper;
import com.cibertec.proyectosw2.repository.ProductoRepository;
import com.cibertec.proyectosw2.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repo;

    @Override
    public ProductoDto crear(ProductoDto dto) {
        Producto saved = repo.save(ProductoMapper.toEntity(dto));
        return ProductoMapper.toDto(saved);
    }

    @Override
    public List<ProductoDto> listar() {
        return repo.findByEstadoTrue()
                .stream()
                .map(ProductoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoDto actualizar(Long id, ProductoDto dto) {
        Producto p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));

        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());

        return ProductoMapper.toDto(repo.save(p));
    }

    @Override
    public void eliminar(Long id) {
        Producto p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        p.setEstado(false);          // soft delete
        repo.save(p);
    }

    @Override
    public Producto obtenerEntidad(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
    }
}
