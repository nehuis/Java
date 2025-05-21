package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO utilizado para indicar un producto específico y la cantidad deseada en una venta.")
public class ProductoVentaDTO {

    @Schema(description = "ID del producto a vender", example = "5")
    private Long productoId;

    @Schema(description = "Cantidad de unidades del producto", example = "3")
    private int cantidad;
}