package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
@Schema(description = "Modelo de detalle de venta")
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Schema(description = "ID del detalle de venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonBackReference
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Iphone 16")
    @Column(name = "nombre", nullable = false)
    private String nombreProducto;

    @Schema(description = "Cantidad del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1500")
    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @Schema(description = "Precio total de la compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "3000")
    @Column(name = "subtotal", nullable = false)
    private double subtotal;
}
