package com.cibertec.proyectosw2.mapper;

// CAMBIO: Importar el DTO correcto
import com.cibertec.proyectosw2.dto.VentaItemRequestDto;
import com.cibertec.proyectosw2.dto.VentaResponseDto;
import com.cibertec.proyectosw2.entity.Producto;
import com.cibertec.proyectosw2.entity.Usuario;
import com.cibertec.proyectosw2.entity.Venta;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class VentaMapper {

    /**
     * Convierte un VentaItemRequestDto a una entidad Venta.
     * Esta conversión es parcial, ya que requiere objetos 'Producto' y 'Usuario'
     * que deben ser obtenidos y asignados en el servicio.
     * También calcula el total de la venta para este ítem.
     *
     * @param itemDto El DTO del ítem de entrada.
     * @param producto El producto que se está vendiendo.
     * @param vendedor El usuario que realiza la venta.
     * @return Una entidad Venta lista para ser guardada.
     */
    // CAMBIO: La firma del método ahora usa VentaItemRequestDto
    public Venta toEntity(VentaItemRequestDto itemDto, Producto producto, Usuario vendedor) {
        // CAMBIO: Usar itemDto para obtener la cantidad
        BigDecimal total = producto.getPrecio().multiply(new BigDecimal(itemDto.getCantidad()));

        return Venta.builder()
                .producto(producto)
                .cantidad(itemDto.getCantidad()) // CAMBIO: Usar itemDto
                .precioUnitarioAlVender(producto.getPrecio()) // Se congela el precio
                .totalVenta(total) // Se calcula y se guarda
                .fecha(LocalDateTime.now())
                .vendedor(vendedor)
                .build();
    }

    /**
     * Convierte una entidad Venta a un VentaResponseDto.
     * (Este método ya estaba bien, no necesita cambios).
     */
    public VentaResponseDto toResponseDto(Venta entity) {
        return VentaResponseDto.builder()
                .id(entity.getId())
                .productoId(entity.getProducto().getId())
                .productoNombre(entity.getProducto().getNombre())
                .cantidad(entity.getCantidad())
                .precioUnitarioAlVender(entity.getPrecioUnitarioAlVender())
                .totalVenta(entity.getTotalVenta())
                .fecha(entity.getFecha())
                .vendedorId(entity.getVendedor().getId())
                .vendedorUsername(entity.getVendedor().getUsername())
                .build();
    }
}