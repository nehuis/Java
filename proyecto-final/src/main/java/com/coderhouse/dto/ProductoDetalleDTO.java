package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de detalle del producto")
public class ProductoDetalleDTO {
	
	@Schema(description = "Nombre del producto", example = "Iphone 16")
    private String nombreProducto;
    
	@Schema(description = "Cantidad del producto", example = "2")
    private int cantidad;
    
	@Schema(description = "Precio unitario del producto", example = "2000")
    private double precioUnitario;
    
	@Schema(description = "Subtotal del producto", example = "4000")
    private double subtotal; 

    public ProductoDetalleDTO(String nombreProducto, int cantidad, double precioUnitario, double subtotal) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

}
