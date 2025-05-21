package com.coderhouse.dto;

import java.sql.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de comprobante")
public class ComprobanteDTO {
	
	@Schema(description = "Id de comprobante", example = "1")
	private Long id;
	
	@Schema(description = "Nombre del cliente", example = "Nehuel")
    private String cliente;
    
	@Schema(description = "Fecha de la compra", example = "2025-05-19")
    private Date fecha;
    
	@Schema(description = "Cantidad total de productos", example = "2")
    private int cantidadTotal;
    
	@Schema(description = "Precio total", example = "$2000")
    private double total;
    
	@Schema(description = "Lista de productos", example = "[Iphone 16, Playstation 5]")
    private List<ProductoDetalleDTO> productos;

	
    public ComprobanteDTO(Long id, String cliente, Date fecha, int cantidadTotal, double total, List<ProductoDetalleDTO> productos) {
        this.id = id;
    	this.cliente = cliente;
        this.fecha = fecha;
        this.cantidadTotal = cantidadTotal;
        this.total = total;
        this.productos = productos;
    }

}
