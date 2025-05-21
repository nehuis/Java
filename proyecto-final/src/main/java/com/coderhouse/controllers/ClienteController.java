package com.coderhouse.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.coderhouse.dto.ClienteDTO;
import com.coderhouse.dto.ErrorResponseDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Gestión de clientes", description = "Endpoints para gestionar clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Operation(summary = "Obtener la lista de todos los clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente", content = { 
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }), 
			
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ClienteDTO>> getAllClientes() {
	    try {
	        List<Cliente> clientes = clienteService.findAll();
	        List<ClienteDTO> clientesDTO = clientes.stream()
	            .map(ClienteDTO::new)
	            .collect(Collectors.toList());
	        return ResponseEntity.ok(clientesDTO);
	    } catch (Exception err) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	@Operation(summary = "Obtener cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente obtenido correctamente", content = { 
					@Content(mediaType = "aplicattion/json", schema = @Schema(implementation = Cliente.class)) }), 
			@ApiResponse(responseCode = "404", description = "Error al intentar obtener el cliente, o el cliente no existe", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping("/{clienteId}")
	public ResponseEntity<?> getClienteById(@PathVariable long clienteId) {
	    try {
	        Cliente cliente = clienteService.findById(clienteId); 
	        ClienteDTO clienteDTO = new ClienteDTO(
	            cliente.getId(),
	            cliente.getNombre(),
	            cliente.getApellido(),
	            cliente.getDni(),
	            cliente.getEmail()
	        );
	        return ResponseEntity.ok(clienteDTO);
	    } catch (IllegalArgumentException err) {
	        ErrorResponseDTO error = new ErrorResponseDTO("Cliente con ID " + clienteId + " no encontrado.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	    } catch (Exception err) {
	        ErrorResponseDTO error = new ErrorResponseDTO("Error interno al buscar el cliente.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
	}

	
	@Operation(summary = "Crear un nuevo cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente creado correctamente", content = { 
					@Content(mediaType = "aplicattion/json", schema = @Schema(implementation = Cliente.class)) }), 
			@ApiResponse(responseCode = "404", description = "Error al intentar crear el cliente", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping("/create")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteCreado = clienteService.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
		} catch (IllegalArgumentException Err) {
			return ResponseEntity.notFound().build();
		} catch(Exception err) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary = "Editar un cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente editado correctamente", content = { 
					@Content(mediaType = "aplicattion/json", schema = @Schema(implementation = Cliente.class)) }), 
			@ApiResponse(responseCode = "404", description = "Error al intentar editar el cliente, o el cliente no existe", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
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
	
	@Operation(summary = "Eliminar un cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente", content = { 
					@Content() }), 
			@ApiResponse(responseCode = "409", description = "Error al intentar eliminar el cliente, o el cliente no existe", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "aplicattion/json", schema = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<?> deleteClienteById(@PathVariable Long clienteId) {
	    try {
	        clienteService.deleteById(clienteId);
	        return ResponseEntity.noContent().build(); // 204
	    } catch (IllegalArgumentException e) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", "Cliente no encontrado");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404
	    } catch (IllegalStateException e) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", "No se puede eliminar el cliente porque tiene ventas asociadas");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409
	    } catch (Exception e) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", "Error interno al eliminar cliente");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); // 500
	    }
	}
}
