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
@Table(name = "producciones")
public class Produccion {

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

    // --- MEJORA #1: Se guarda el costo unitario del producto EN EL MOMENTO de la producción ---
    // Permite una valoración precisa del inventario, especialmente si los costos varían.
    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costoUnitarioAlProducir;

    // --- MEJORA #2: Se añade el costo total del lote de producción ---
    // Calculado como (cantidad * costoUnitarioAlProducir).
    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal costoTotalProduccion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "operario_id", nullable = false)
    private Usuario operario;
}