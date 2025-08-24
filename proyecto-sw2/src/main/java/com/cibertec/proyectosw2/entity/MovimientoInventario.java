package com.cibertec.proyectosw2.entity;

import com.cibertec.proyectosw2.entity.enums.TipoMovimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

// --- MEJORA FUNDAMENTAL: Nueva entidad para la trazabilidad del inventario ---
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    // La cantidad será POSITIVA para entradas (Producción)
    // y NEGATIVA para salidas (Venta, Merma).
    @NotNull
    @Column(nullable = false)
    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoMovimiento tipo;

    // ID de la entidad que originó el movimiento (Venta, Produccion, Merma, etc.)
    // Esto permite navegar desde el movimiento a la transacción específica.
    private Long origenId;

    // Almacena el stock del producto DESPUÉS de que este movimiento se aplicara.
    // Es muy útil para auditorías y para reconstruir el historial de stock.
    @NotNull
    @Column(nullable = false)
    private Integer stockResultante;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 255)
    private String motivo;
}

// Este Enum debe estar en su propio archivo, p.ej., package com.cibertec.proyectosw2.entity.enums;
// public enum TipoMovimiento { PRODUCCION, VENTA, MERMA, AJUSTE_INICIAL, CORRECCION }