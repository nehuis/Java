package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	
	@Column(name = "Apellido", nullable = false)
	private String apellido;
	
	@Column(name = "DNI", nullable = false, unique = true)
	private int dni;
	
	@Column(name = "Email", nullable = false, unique = true)
	private String email;
	
	@ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
	private List<Producto> productos = new ArrayList<>(); 
	
	private LocalDateTime createdAt;

	public Cliente(String string) {
		super();
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email="
				+ email + ", productos=" + productos + ", createdAt=" + createdAt + "]";
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Cliente(String nombre, String apellido, int dni, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
	}

	public Cliente(long id, String nombre, String apellido, int dni, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
