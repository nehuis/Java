package com.coderhouse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.DetalleVenta;
import com.coderhouse.models.Venta;

public interface DetalleRepository extends JpaRepository<DetalleVenta, Long>{
	
	List<DetalleVenta> findByVenta(Venta venta);

}
