package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.VentaItemRequestDto;
import com.cibertec.proyectosw2.dto.VentaRequestDto;
import com.cibertec.proyectosw2.dto.VentaResponseDto;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.entity.Venta;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.VentaMapper;
import com.cibertec.proyectosw2.repository.ProductoRepository;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import com.cibertec.proyectosw2.repository.VentaRepository;
import com.cibertec.proyectosw2.service.InventarioService;
import com.cibertec.proyectosw2.service.VentaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final VentaMapper ventaMapper;
    private final InventarioService inventarioService;

    @Override
    @Transactional
    // CAMBIO: El método ahora retorna List<VentaResponseDto>
    public List<VentaResponseDto> registrarVenta(VentaRequestDto ventaDto, Long vendedorId) {
        // 1. Obtener el vendedor una sola vez
        Usuario vendedor = usuarioRepository.findById(vendedorId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario vendedor", vendedorId));

        List<Venta> ventasCreadas = new ArrayList<>();

        // 2. Iterar sobre cada ítem en la solicitud de venta
        for (VentaItemRequestDto itemDto : ventaDto.getItems()) {

            // 3. Buscar el producto y validar stock para este ítem
            Producto producto = productoRepository.findById(itemDto.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto", itemDto.getProductoId()));

            if (producto.getStock() < itemDto.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // 4. Usar el mapper (ya corregido) para crear la entidad Venta para este ítem
            Venta nuevaVenta = ventaMapper.toEntity(itemDto, producto, vendedor);

            // 5. Guardar el registro de venta individual
            Venta ventaGuardada = ventaRepository.save(nuevaVenta);
            ventasCreadas.add(ventaGuardada);

            // 6. Actualizar el stock del producto
            producto.setStock(producto.getStock() - itemDto.getCantidad());
            // No es necesario llamar a productoRepository.save() si la entidad está gestionada por JPA
            // dentro de una transacción. El cambio se persistirá al final.

            // 7. Registrar el movimiento de inventario (si es necesario)
            // inventarioService.registrarMovimientoDesdeVenta(ventaGuardada);
        }

        // 8. Convertir la lista de entidades Venta guardadas a una lista de DTOs de respuesta
        return ventasCreadas.stream()
                .map(ventaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponseDto obtenerVentaPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta", id));
        return ventaMapper.toResponseDto(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDto> listarTodasLasVentas() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}