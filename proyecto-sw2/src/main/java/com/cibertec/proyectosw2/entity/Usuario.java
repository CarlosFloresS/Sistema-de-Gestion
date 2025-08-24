package com.cibertec.proyectosw2.entity;

import com.cibertec.proyectosw2.entity.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usuarios",
        uniqueConstraints = @UniqueConstraint(name = "uk_usuario_username", columnNames = "username"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String username;

    @NotBlank
    @Size(min = 60, max = 60)
    private String password;

    @Builder.Default
    private Boolean estado = true;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}