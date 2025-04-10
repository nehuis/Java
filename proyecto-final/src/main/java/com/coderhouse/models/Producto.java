package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name = "Productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Nombre", nullable = false, unique = true)
	private String nombre;
	
	@Column(name = "Precio", nullable = false)
	private double precio;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
				name = "cliente_producto",
				joinColumns = @JoinColumn(name = "producto_id"),
				inverseJoinColumns = @JoinColumn(name = "cliente_id")
			)
	private List<Cliente> clientes = new ArrayList<>();

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(long id, String nombre, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Producto(String nombre, double precio) {
	    this.nombre = nombre;
	    this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", clientes=" + clientes + "]";
	}
	
	
}
