package com.cibertec.proyectosw2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "productos", uniqueConstraints = @UniqueConstraint(name = "uk_producto_nombre", columnNames = "nombre"))
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, unique = true) // Asegurar que el nombre no sea nulo y sea único a nivel de BD
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2) // Precio de VENTA
    private BigDecimal precio;

    // --- MEJORA #1: Añadido campo 'costo' ---
    // Representa el costo de producción o adquisición. Es vital para calcular rentabilidad.
    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    // --- MEJORA #2: 'stock' es ahora un campo cacheado ---
    // Este campo se actualizará de forma controlada por los servicios
    // cada vez que se cree un MovimientoInventario. No debe ser modificable directamente.
    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer stock;

    @Builder.Default
    private Boolean estado = true;
}