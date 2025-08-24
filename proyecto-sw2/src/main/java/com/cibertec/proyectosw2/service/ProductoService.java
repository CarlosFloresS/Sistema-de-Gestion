package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.ProductoRequestDto;
import com.cibertec.proyectosw2.dto.ProductoResponseDto;
import java.util.List;

public interface ProductoService {
    ProductoResponseDto registrarProducto(ProductoRequestDto productoDto);
    ProductoResponseDto actualizarProducto(Long id, ProductoRequestDto productoDto);
    ProductoResponseDto obtenerProductoPorId(Long id);
    List<ProductoResponseDto> listarTodosLosProductos();
    List<ProductoResponseDto> listarProductosActivos();
    void desactivarProducto(Long id);
    void activarProducto(Long id);
}