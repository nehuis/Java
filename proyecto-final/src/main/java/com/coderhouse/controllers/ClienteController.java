package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity <List<Cliente>> getAllClientes(){
		try {
			List<Cliente> clientes = clienteService.findAll();
			return ResponseEntity.ok(clientes);
		} catch (Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable long clienteId){
		try {
			
				Cliente cliente = clienteService.findById(clienteId);
				return ResponseEntity.ok(cliente);
				
		} catch (IllegalArgumentException Err) {
			return ResponseEntity.notFound().build();
		} catch (Exception Err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteCreado = clienteService.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
		} catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> updateClienteById(@PathVariable Long clienteId, @RequestBody Cliente clienteActualizado){
		try {
			Cliente cliente = clienteService.update(clienteId, clienteActualizado);
			return ResponseEntity.ok(cliente);	
		} catch (IllegalArgumentException Err) {
			return ResponseEntity.notFound().build();
		} catch (Exception Err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable Long clienteId) {
		try {
			clienteService.deleteById(clienteId);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException Err) {
			return ResponseEntity.notFound().build();
		} catch (Exception Err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
