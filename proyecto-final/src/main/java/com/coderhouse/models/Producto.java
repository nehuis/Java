package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Schema(description = "Modelo de producto")
@Table(name = "Productos")
public class Producto {
	
	@Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Celular")
	@Column(name = "Nombre", nullable = false, unique = true)
	private String nombre;
	
	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "$250")
	@Column(name = "Precio", nullable = false)
	private double precio;
	
	@Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
	@Column(name = "Stock", nullable = false)
	private int stock;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
				name = "cliente_producto",
				joinColumns = @JoinColumn(name = "producto_id"),
				inverseJoinColumns = @JoinColumn(name = "cliente_id")
			)
	@JsonIgnore
	private List<Cliente> clientes = new ArrayList<>();

}
