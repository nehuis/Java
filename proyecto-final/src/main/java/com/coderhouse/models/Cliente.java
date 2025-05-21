package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Schema(description = "Modelo de cliente")
@Table(name = "Clientes")
public class Cliente {
	
	@Schema(description = "ID del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Nombre del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Hernan")
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	
	@Schema(description = "Apellido del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Lopez")
	@Column(name = "Apellido", nullable = false)
	private String apellido;
	
	@Schema(description = "DNI del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "22658964")
	@Column(name = "DNI", nullable = false, unique = true)
	private int dni;
	
	@Schema(description = "Email del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "hernan@gmail.com")
	@Column(name = "Email", nullable = false, unique = true)
	private String email;
	
	@Schema(description = "Lista de productos que tiene el cliente")
	@ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
	private List<Producto> productos = new ArrayList<>(); 
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Venta> ventas = new ArrayList<>();
	
	@Schema(description = "Fecha de la compra")
	private LocalDateTime createdAt;
	
}
