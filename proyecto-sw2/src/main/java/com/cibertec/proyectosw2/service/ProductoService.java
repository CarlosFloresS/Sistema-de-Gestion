package com.cibertec.proyectosw2.service;

import com.cibertec.proyectosw2.dto.ProductoDto;
import com.cibertec.proyectosw2.entity.Producto;

import java.util.List;

public interface ProductoService {
    ProductoDto crear(ProductoDto dto);
    List<ProductoDto> listar();
    ProductoDto actualizar(Long id, ProductoDto dto);
    void eliminar(Long id);           // deshabilita (soft-delete)
    Producto obtenerEntidad(Long id); // uso interno
}
