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
@Table(name = "mermas")
public class Merma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Producto afectado por la merma */
    @ManyToOne(optional = false)
    private Producto producto;

    /* Cantidad desperdiciada */
    @Positive
    private Integer cantidad;

    /* Descripción o causa de la merma */
    @Size(max = 255)
    private String motivo;

    /* Fecha-hora en la que se registra */
    private LocalDateTime fecha;

    /* Operario / usuario que registra la merma */
    @ManyToOne
    private Usuario operario;
}
