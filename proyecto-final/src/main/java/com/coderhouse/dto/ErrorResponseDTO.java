package com.coderhouse.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de comprobante")
public class ErrorResponseDTO {
	
	@Schema(description = "Mensaje de error", example = "Cliente no encontrado")
    private String mensaje;
	
    @Schema(description = "Fecha y hora del error")
    private LocalDateTime timestamp = LocalDateTime.now();
    
    public ErrorResponseDTO(String mensaje) {
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

}
