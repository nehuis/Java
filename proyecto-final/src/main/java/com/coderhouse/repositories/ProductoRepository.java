package com.coderhouse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	Optional<Producto> findByNombre(String nombre);

}
