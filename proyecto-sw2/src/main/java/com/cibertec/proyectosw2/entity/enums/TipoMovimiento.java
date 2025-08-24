package com.cibertec.proyectosw2.entity.enums;

/**
 * Representa los diferentes tipos de movimientos que pueden afectar el stock de un producto.
 * Cada tipo de movimiento tiene una implicación directa en el inventario.
 */
public enum TipoMovimiento {

    /**
     * Representa una entrada de stock debido a la fabricación o producción de nuevos productos.
     * Este movimiento siempre incrementa el stock (cantidad positiva).
     */
    PRODUCCION,

    /**
     * Representa una salida de stock debido a una venta a un cliente.
     * Este movimiento siempre decrementa el stock (cantidad negativa).
     */
    VENTA,

    /**
     * Representa una salida de stock debido a productos dañados, caducados o perdidos.
     * Este movimiento siempre decrementa el stock (cantidad negativa).
     */
    MERMA,

    /**
     * Representa la carga inicial de stock cuando se registra un producto por primera vez
     * o al iniciar el sistema.
     * Este movimiento incrementa el stock (cantidad positiva).
     */
    AJUSTE_INICIAL,

    /**
     * Representa un ajuste manual del stock, ya sea positivo o negativo, para corregir
     * discrepancias encontradas en un conteo físico del inventario.
     */
    CORRECCION
}