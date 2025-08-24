package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.MermaRequestDto;
import com.cibertec.proyectosw2.dto.MermaResponseDto;
import com.cibertec.proyectosw2.entity.Merma;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.MermaMapper;
import com.cibertec.proyectosw2.repository.MermaRepository;
import com.cibertec.proyectosw2.repository.ProductoRepository;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import com.cibertec.proyectosw2.service.InventarioService;
import com.cibertec.proyectosw2.service.MermaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MermaServiceImpl implements MermaService {

    private final MermaRepository mermaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final MermaMapper mermaMapper;
    private final InventarioService inventarioService;

    @Override
    @Transactional
    public MermaResponseDto registrarMerma(MermaRequestDto mermaDto, Long operarioId) {
        Producto producto = productoRepository.findById(mermaDto.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", mermaDto.getProductoId()));

        Usuario operario = usuarioRepository.findById(operarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", operarioId));

        if (producto.getStock() < mermaDto.getCantidad()) {
            throw new IllegalStateException("No se puede registrar una merma mayor al stock actual para el producto: " + producto.getNombre());
        }

        Merma nuevaMerma = mermaMapper.toEntity(mermaDto, producto, operario);
        Merma mermaGuardada = mermaRepository.save(nuevaMerma);

        inventarioService.registrarMovimientoDesdeMerma(mermaGuardada);

        return mermaMapper.toResponseDto(mermaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public MermaResponseDto obtenerMermaPorId(Long id) {
        Merma merma = mermaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Merma", id));
        return mermaMapper.toResponseDto(merma);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MermaResponseDto> listarTodasLasMermas() {
        return mermaRepository.findAll().stream()
                .map(mermaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}