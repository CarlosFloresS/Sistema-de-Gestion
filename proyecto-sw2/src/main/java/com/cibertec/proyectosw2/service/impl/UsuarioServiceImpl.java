package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.UsuarioCreateDto;
import com.cibertec.proyectosw2.dto.UsuarioDto;
import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.mapper.UsuarioMapper;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import com.cibertec.proyectosw2.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public UsuarioDto crear(UsuarioCreateDto dto) {
        Usuario nuevo = UsuarioMapper.toEntity(dto, encoder);
        return UsuarioMapper.toDto(repo.save(nuevo));
    }

    @Override
    public List<UsuarioDto> listar() {
        return repo.findAll().stream().map(UsuarioMapper::toDto).toList();
    }

    @Override
    public UsuarioDto cambiarEstado(Long id, boolean activo) {
        Usuario u = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        u.setEstado(activo);
        return UsuarioMapper.toDto(repo.save(u));
    }
}