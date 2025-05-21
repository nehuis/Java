package com.coderhouse.controllers;

import com.coderhouse.dto.ComprobanteDTO;
import com.coderhouse.dto.VentaRequestDTO;
import com.coderhouse.services.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Map;

@RestController
@RequestMapping("/ventas")
@Tag(name = "Gestión de ventas", description = "Endpoints para gestionar ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Simular una venta con nombre de productos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta simulada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en los datos de entrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/simular")
    public ResponseEntity<Map<String, Object>> simularVenta(@RequestBody VentaRequestDTO request) {
        Map<String, Object> resultado = ventaService.simularVenta(request.getClienteId(), request.getProductos());

        if (resultado.containsKey("error") || resultado.containsKey("errores")) {
            return ResponseEntity.badRequest().body(resultado);
        }

        return ResponseEntity.ok(resultado);
    }

    @Operation(summary = "Obtener comprobante por ID de venta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comprobante obtenido correctamente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/comprobante/{id}")
    public ResponseEntity<?> obtenerComprobante(@PathVariable Long id) {
        try {
            ComprobanteDTO comprobante = ventaService.obtenerComprobante(id);
            return ResponseEntity.ok(comprobante);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }
    
    @Operation(summary = "Descargar comprobante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comprobante descargado correctamente"),
        @ApiResponse(responseCode = "404", description = "Comprobante no encontrado")
    })
    @GetMapping("/comprobante/{id}/pdf")
    public ResponseEntity<byte[]> descargarComprobante(@PathVariable Long id) {
        try {
            ComprobanteDTO comprobante = ventaService.obtenerComprobante(id);
            ByteArrayInputStream pdfStream = ventaService.generarComprobantePDF(comprobante);
            byte[] pdfBytes = pdfStream.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(pdfBytes.length);
            headers.setContentDispositionFormData("attachment", "comprobante_" + id + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            e.printStackTrace(); // Ver en consola qué ocurrió
            return ResponseEntity.status(500).body(null);
        }
    }
}
