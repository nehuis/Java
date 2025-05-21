package com.coderhouse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Schema(description = "Modelo de ventas")
@Table(name = "Ventas")
public class Venta {
	
	@Id
	@Schema(description = "ID de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonBackReference
	private Cliente cliente;
	
	@Schema(description = "Fecha de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-05-19")
	private Date fecha;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DetalleVenta> detalles = new ArrayList<>();

}
