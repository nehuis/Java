package com.coderhouse.dto;

import com.coderhouse.models.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
	
	 private Long id;
	 private String nombre;
	 private String apellido;
	 private int dni;
	 private String email;
	  
	 public ClienteDTO(Cliente cliente) {
		 this.id = cliente.getId();
		 this.nombre = cliente.getNombre();
	     this.apellido = cliente.getApellido();
	     this.dni = cliente.getDni();
	     this.email = cliente.getEmail();
	    }
}
