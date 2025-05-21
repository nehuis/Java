package com.coderhouse.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO que representa la solicitud para registrar una venta.")
public class VentaRequestDTO {

    @Schema(description = "ID del cliente que realiza la compra", example = "1")
    private Long clienteId;

    @Schema(
        description = "Mapa con los nombres de productos como clave y la cantidad deseada como valor",
        example = "{\"iPhone 16\": 2, \"PC gamer\": 1}"
    )
    private Map<String, Integer> productos;
}
