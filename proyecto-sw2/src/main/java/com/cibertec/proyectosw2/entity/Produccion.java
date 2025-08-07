package com.cibertec.proyectosw2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "producciones")
public class Produccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Producto fabricado */
    @ManyToOne(optional = false)
    private Producto producto;

    /* Cantidad producida */
    @Positive
    private Integer cantidad;

    /* Fecha-hora de registro */
    private LocalDateTime fecha;

    /* Operario que registra (usuario logueado) */
    @ManyToOne
    private Usuario operario;
}
