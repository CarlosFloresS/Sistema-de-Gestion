package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.AjusteInventarioRequestDto;
import com.cibertec.proyectosw2.dto.MovimientoInventarioResponseDto;
import com.cibertec.proyectosw2.entity.*;
import com.cibertec.proyectosw2.entity.enums.TipoMovimiento;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.MovimientoInventarioMapper;
import com.cibertec.proyectosw2.repository.MovimientoInventarioRepository;
import com.cibertec.proyectosw2.repository.ProductoRepository;
import com.cibertec.proyectosw2.service.InventarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final ProductoRepository productoRepository;
    private final MovimientoInventarioRepository movimientoRepository;
    private final MovimientoInventarioMapper movimientoMapper;

    // --- CAMBIO #1: El método ahora es 'public' para que @Transactional funcione ---
    @Transactional(propagation = Propagation.MANDATORY)
    public void actualizarStockYRegistrarMovimiento(Producto producto, int cantidad, TipoMovimiento tipo, Long origenId, String motivo) {
        int stockActual = producto.getStock();
        int stockResultante = stockActual + cantidad;

        if (stockResultante < 0) {
            throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        producto.setStock(stockResultante);
        productoRepository.save(producto);

        MovimientoInventario movimiento = MovimientoInventario.builder()
                .producto(producto)
                .cantidad(cantidad)
                .tipo(tipo)
                .origenId(origenId)
                .stockResultante(stockResultante)
                .fecha(LocalDateTime.now())
                .motivo(motivo) // --- CAMBIO #2: El campo 'motivo' ahora se asigna aquí ---
                .build();

        movimientoRepository.save(movimiento);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void registrarMovimientoDesdeVenta(Venta venta) {
        // Las ventas no tienen un motivo, por eso se pasa null.
        actualizarStockYRegistrarMovimiento(venta.getProducto(), -venta.getCantidad(), TipoMovimiento.VENTA, venta.getId(), null);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void registrarMovimientoDesdeProduccion(Produccion produccion) {
        // La producción tampoco tiene un motivo, se pasa null.
        actualizarStockYRegistrarMovimiento(produccion.getProducto(), produccion.getCantidad(), TipoMovimiento.PRODUCCION, produccion.getId(), null);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void registrarMovimientoDesdeMerma(Merma merma) {
        // --- CAMBIO #3: Se pasa el motivo de la merma al método central ---
        actualizarStockYRegistrarMovimiento(merma.getProducto(), -merma.getCantidad(), TipoMovimiento.MERMA, merma.getId(), merma.getMotivo());
    }

    @Override
    @Transactional
    public MovimientoInventarioResponseDto registrarAjusteInventario(AjusteInventarioRequestDto ajusteDto, Long usuarioId) {
        Producto producto = productoRepository.findById(ajusteDto.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", ajusteDto.getProductoId()));

        // --- CAMBIO #4: Se pasa el motivo del ajuste al método central ---
        actualizarStockYRegistrarMovimiento(producto, ajusteDto.getCantidadAjuste(), TipoMovimiento.CORRECCION, usuarioId, ajusteDto.getMotivo());

        MovimientoInventario ultimoMovimiento = movimientoRepository.findTopByProductoIdOrderByFechaDesc(producto.getId()).get();
        return movimientoMapper.toResponseDto(ultimoMovimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public int getStockActual(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", productoId));
        return producto.getStock();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoInventarioResponseDto> getHistorialDeMovimientos(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new ResourceNotFoundException("Producto", productoId);
        }
        return movimientoRepository.findByProductoIdOrderByFechaAsc(productoId).stream()
                .map(movimientoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}