package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.ProduccionRequestDto;
import com.cibertec.proyectosw2.dto.ProduccionResponseDto;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.entity.Produccion;
import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.ProduccionMapper;
import com.cibertec.proyectosw2.repository.ProductoRepository;
import com.cibertec.proyectosw2.repository.ProduccionRepository;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import com.cibertec.proyectosw2.service.InventarioService;
import com.cibertec.proyectosw2.service.ProduccionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProduccionServiceImpl implements ProduccionService {

    private final ProduccionRepository produccionRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProduccionMapper produccionMapper;
    private final InventarioService inventarioService;

    @Override
    @Transactional
    public ProduccionResponseDto registrarProduccion(ProduccionRequestDto produccionDto, Long operarioId) {
        Producto producto = productoRepository.findById(produccionDto.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", produccionDto.getProductoId()));

        Usuario operario = usuarioRepository.findById(operarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", operarioId));

        Produccion nuevaProduccion = produccionMapper.toEntity(produccionDto, producto, operario);
        Produccion produccionGuardada = produccionRepository.save(nuevaProduccion);

        inventarioService.registrarMovimientoDesdeProduccion(produccionGuardada);

        return produccionMapper.toResponseDto(produccionGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public ProduccionResponseDto obtenerProduccionPorId(Long id) {
        Produccion produccion = produccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producción", id));
        return produccionMapper.toResponseDto(produccion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduccionResponseDto> listarTodasLasProducciones() {
        return produccionRepository.findAll().stream()
                .map(produccionMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}