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

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer cantidad;

    // --- MEJORA #1: Se guarda el precio unitario del producto EN EL MOMENTO de la venta ---
    // Esto asegura que el registro histórico sea correcto aunque el precio del producto cambie.
    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitarioAlVender;

    // --- MEJORA #2: Se renombra 'total' a 'totalVenta' por claridad ---
    // Este valor es calculado (cantidad * precioUnitarioAlVender) y guardado.
    @NotNull
    @Positive
    @Column(name = "total", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalVenta;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;
}