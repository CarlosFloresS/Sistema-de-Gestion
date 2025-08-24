package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.ProductoRequestDto;
import com.cibertec.proyectosw2.dto.ProductoResponseDto;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.ProductoMapper;
import com.cibertec.proyectosw2.repository.ProductoRepository;
import com.cibertec.proyectosw2.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional
    public ProductoResponseDto registrarProducto(ProductoRequestDto productoDto) {
        productoRepository.findByNombreIgnoreCase(productoDto.getNombre()).ifPresent(p -> {
            throw new IllegalArgumentException("Ya existe un producto con el nombre: " + productoDto.getNombre());
        });

        Producto producto = productoMapper.toEntity(productoDto);
        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.toResponseDto(productoGuardado);
    }

    @Override
    @Transactional
    public ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto productoDto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));

        productoExistente.setNombre(productoDto.getNombre());
        productoExistente.setDescripcion(productoDto.getDescripcion());
        productoExistente.setPrecio(productoDto.getPrecio());
        productoExistente.setCosto(productoDto.getCosto());

        Producto productoActualizado = productoRepository.save(productoExistente);
        return productoMapper.toResponseDto(productoActualizado);
    }

    @Override
    @Transactional
    public ProductoResponseDto obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        return productoMapper.toResponseDto(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDto> listarTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDto> listarProductosActivos() {
        return productoRepository.findByEstado(true).stream()
                .map(productoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void desactivarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        producto.setEstado(false);
        productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void activarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        producto.setEstado(true);
        productoRepository.save(producto);
    }
}