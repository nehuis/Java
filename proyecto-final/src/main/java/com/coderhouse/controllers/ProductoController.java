package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Producto;
import com.coderhouse.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos(){
		try {
			List<Producto> productos = productoService.findAll();
			return ResponseEntity.ok(productos);
		} catch (Exception err) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{productoId}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long productoId){
		try {
				Producto producto = productoService.findById(productoId);
				return ResponseEntity.ok(producto);
			} catch(IllegalArgumentException err) {
				return ResponseEntity.notFound().build();		
		} 		catch(Exception err) {
			return ResponseEntity.internalServerError().build();
		} 
	} 
	
	@PostMapping("/create")
	public Producto createProducto(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/{productoId}")
	public ResponseEntity<Producto> updateProductoById(@PathVariable Long productoId, @RequestBody Producto productoActualizado){
		try {
			Producto updatedProducto = productoService.update(productoId, productoActualizado);
			return ResponseEntity.ok(updatedProducto);
		} catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build();		
		} catch(Exception err) {
		return ResponseEntity.internalServerError().build();
		} 
	}
	
	@DeleteMapping("/{productoId}")
	public ResponseEntity<Void> deleteProductoById(@PathVariable Long productoId) {
		try {
			productoService.deleteById(productoId);
			return ResponseEntity.noContent().build();
		} catch(IllegalArgumentException err) {
			return ResponseEntity.notFound().build();		
		} catch(Exception err) {
		return ResponseEntity.internalServerError().build();
		} 
	}
}