package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de detalle del producto")
public class ProductoDetalleDTO {
	
    private String nombreProducto;
    
    private int cantidad;
    
    private double precioUnitario;
    
    private double subtotal; 

    public ProductoDetalleDTO(String nombreProducto, int cantidad, double precioUnitario, double subtotal) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

}
