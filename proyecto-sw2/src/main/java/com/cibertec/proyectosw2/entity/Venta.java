package com.cibertec.proyectosw2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Producto vendido */
    @ManyToOne(optional = false)
    private Producto producto;

    /* Cantidad de unidades */
    @Positive
    private Integer cantidad;

    /* Importe total (precio × cantidad) */
    @Positive
    private BigDecimal total;

    /* Fecha-hora de venta */
    private LocalDateTime fecha;

    /* Vendedor que registra la venta */
    @ManyToOne
    private Usuario vendedor;
}
