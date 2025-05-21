package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de productos", description = "Endpoints para gestionar productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Operation(summary = "Obtener la lista de todos los productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", content = { 
					@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }), 
			
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos(){
		try {
			List<Producto> productos = productoService.findAll();
			return ResponseEntity.ok(productos);
		} catch (Exception err) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@Operation(summary = "Obtener producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto obtenido correctamente", content = { 
					@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }), 
			@ApiResponse(responseCode = "404", description = "Error al intentar obtener el producto, o el producto no existe", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
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
	
	@Operation(summary = "Crear un producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto creado correctamente", content = { 
					@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }), 
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping("/create")
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto productoNuevo = productoService.save(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
		} catch(Exception err) {
			return ResponseEntity.internalServerError().build();
		} 
	}
	
	@Operation(summary = "Editar un producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto editado correctamente", content = { 
					@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }), 
			@ApiResponse(responseCode = "404", description = "Error al intentar editar el producto, o el producto no existe", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
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
	
	@Operation(summary = "Eliminar un producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto eliminado correctamente", content = { 
					@Content() }), 
			@ApiResponse(responseCode = "404", description = "Error al intentar eliminar el producto, o el producto no existe", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
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