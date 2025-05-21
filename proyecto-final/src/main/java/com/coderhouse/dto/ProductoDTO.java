package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO del producto")
public class ProductoDTO {
	
	@Schema(description = "Nombre del producto", example = "Playstation 5")
    private String nombre;

}
