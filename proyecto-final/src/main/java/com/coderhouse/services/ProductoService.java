package com.coderhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.interfaces.CrudInterface;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService implements CrudInterface<Producto, Long> {
	
	@Autowired
	private ProductoRepository productoRepository;


	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	@Transactional
	public Producto update(Long id, Producto productoActualizado) {
	    Optional<Producto> productoOpt = productoRepository.findById(id);
	    if (productoOpt.isEmpty()) {
	        throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
	    }

	    Producto productoExistente = productoOpt.get();
	    productoExistente.setNombre(productoActualizado.getNombre());
	    productoExistente.setPrecio(productoActualizado.getPrecio());
	    productoExistente.setStock(productoActualizado.getStock());

	    return productoRepository.save(productoExistente);
	}


	@Override
	public void deleteById(Long id) {
		if(!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado");
		}
		productoRepository.deleteById(id);
	}

}