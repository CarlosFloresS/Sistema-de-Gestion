package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.UsuarioCreateDto;
import com.cibertec.proyectosw2.dto.UsuarioDto;
import com.cibertec.proyectosw2.entity.Rol;
import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.exception.ResourceNotFoundException;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioCreateDto usuarioCreateDto;
    private Usuario usuario;
    private UsuarioDto usuarioDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuarioCreateDto = new UsuarioCreateDto();
        usuarioCreateDto.setUsername("carlos");
        usuarioCreateDto.setPasswordPlain("12345678");
        usuarioCreateDto.setRol(Rol.OPERADOR);

        usuario = Usuario.builder()
                .id(1L)
                .username("carlos")
                .password("encodedPass")
                .rol(Rol.OPERADOR)
                .estado(true)
                .build();

        usuarioDto = UsuarioDto.builder()
                .id(1L)
                .username("carlos")
                .rol(Rol.OPERADOR)
                .estado(true)
                .build();
    }

    @Test
    void crear_DeberiaRetornarUsuarioDto() {
        when(passwordEncoder.encode(any())).thenReturn("encodedPass");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDto result = usuarioService.crear(usuarioCreateDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUsername()).isEqualTo("carlos");
        assertThat(result.getRol()).isEqualTo(Rol.OPERADOR);

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void listar_DeberiaRetornarListaDeUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioDto> result = usuarioService.listar();

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getUsername()).isEqualTo("carlos");
        assertThat(result.get(0).getRol()).isEqualTo(Rol.OPERADOR);

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void cambiarEstado_CuandoExisteUsuario_DeberiaCambiarEstado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDto result = usuarioService.cambiarEstado(1L, false);

        assertThat(result).isNotNull();
        assertThat(result.getEstado()).isFalse();

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void cambiarEstado_CuandoNoExisteUsuario_DeberiaLanzarExcepcion() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usuarioService.cambiarEstado(99L, true))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Usuario");

        verify(usuarioRepository, times(1)).findById(99L);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

//    @Test
//    void crear_DeberiaFallarPorNombreIncorrecto() {
//        when(passwordEncoder.encode(any())).thenReturn("encodedPass");
//        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
//
//        UsuarioDto result = usuarioService.crear(usuarioCreateDto);
//
//        // 🚨 Forzamos fallo: esperamos "juan" cuando realmente el username es "carlos"
//        assertThat(result.getUsername()).isEqualTo("juan");
//    }

}
