package com.coderhouse.dto;

import com.coderhouse.models.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de cliente")
public class ClienteDTO {
	
	@Schema(description = "Id de cliente", example = "1")
	 private Long id;
	
	@Schema(description = "Nombre de cliente", example = "Nehuel")
	 private String nombre;
	
	@Schema(description = "Apellido de cliente", example = "Caraballo")
	 private String apellido;
	
	@Schema(description = "DNI de cliente", example = "12438489")
	 private int dni;
	
	@Schema(description = "Email de cliente", example = "nehuel@gmail.com")
	 private String email;
	  
	 public ClienteDTO(Cliente cliente) {
		 this.id = cliente.getId();
		 this.nombre = cliente.getNombre();
	     this.apellido = cliente.getApellido();
	     this.dni = cliente.getDni();
	     this.email = cliente.getEmail();
	    }
}
