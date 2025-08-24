package com.cibertec.proyectosw2.service.impl;

import com.cibertec.proyectosw2.dto.AuthRequestDto;
import com.cibertec.proyectosw2.dto.AuthResponseDto;
import com.cibertec.proyectosw2.entity.enums.Rol;
import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.repository.UsuarioRepository;
import com.cibertec.proyectosw2.security.JwtTokenProvider;
import com.cibertec.proyectosw2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder encoder;

    @Override
    public AuthResponseDto login(AuthRequestDto req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        return new AuthResponseDto(jwtProvider.generarToken(req.getUsername()));
    }

    @Override
    public void crearUsuario(String username, String password, Rol rol) {
        if (usuarioRepo.findByUsername(username).isEmpty()) {
            Usuario u = Usuario.builder()
                    .username(username)
                    .password(encoder.encode(password))
                    .estado(true)
                    .rol(rol)
                    .build();
            usuarioRepo.save(u);
        }
    }
}