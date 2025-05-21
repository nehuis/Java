package com.coderhouse.services;

import com.coderhouse.dto.ComprobanteDTO;
import com.coderhouse.dto.ProductoDetalleDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.DetalleVenta;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.DetalleRepository;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.transaction.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private DateService dateService;
    
    @Transactional
    public Map<String, Object> simularVenta(Long clienteId, Map<String, Integer> productosVendidos) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
        	
            Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            List<String> errores = new ArrayList<>();
            List<DetalleVenta> detalles = new ArrayList<>();
            double total = 0;
            int cantidadTotal = 0;

            for (Map.Entry<String, Integer> entry : productosVendidos.entrySet()) {
                String nombreProducto = entry.getKey();
                int cantidad = entry.getValue();

                Producto producto = productoRepository.findByNombre(nombreProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + nombreProducto));

                if (cantidad <= 0) {
                    errores.add("Cantidad inválida para: " + nombreProducto);
                    continue;
                }

                if (producto.getStock() < cantidad) {
                    errores.add("Stock insuficiente para '" + nombreProducto + "'");
                    continue;
                }

                double subtotal = producto.getPrecio() * cantidad;
                total += subtotal;
                cantidadTotal += cantidad;

                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);

                DetalleVenta detalle = new DetalleVenta();
                detalle.setProducto(producto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(producto.getPrecio());
                detalle.setNombreProducto(producto.getNombre());
                detalle.setSubtotal(subtotal);

                detalles.add(detalle);
            }

            if (!errores.isEmpty()) {
                respuesta.put("errores", errores);
                return respuesta;
            }

            Venta venta = new Venta();
            venta.setCliente(cliente);

            Date fecha = dateService.getDateFromApi();
            if (fecha == null) {
                fecha = new Date();
            }
            venta.setFecha(fecha);
            venta = ventaRepository.save(venta);

            for (DetalleVenta d : detalles) {
                d.setVenta(venta); 
                detalleRepository.save(d);
            }

            List<ProductoDetalleDTO> detalleDTOs = detalles.stream()
                .map(d -> new ProductoDetalleDTO(
                    d.getNombreProducto(),
                    d.getCantidad(),
                    d.getPrecioUnitario(),
                    d.getSubtotal()
                ))
                .toList();

            respuesta.put("idComprobante", venta.getId());
            respuesta.put("fecha", fecha);
            respuesta.put("cliente", cliente.getNombre() + " " + cliente.getApellido());
            respuesta.put("total", total);
            respuesta.put("cantidadTotal", cantidadTotal); 
            respuesta.put("detalle", detalleDTOs);

            return respuesta;

        } catch (Exception e) {
            respuesta.put("error", "Error al registrar venta: " + e.getMessage());
            return respuesta;
        }
    }



    public ComprobanteDTO obtenerComprobante(Long ventaId) {
        Optional<Venta> ventaOpt = ventaRepository.findById(ventaId);
        if (ventaOpt.isEmpty()) {
            throw new RuntimeException("Venta no encontrada con ID: " + ventaId);
        }

        Venta venta = ventaOpt.get();
        Cliente cliente = venta.getCliente();
        List<DetalleVenta> detalles = detalleRepository.findByVenta(venta);

        List<ProductoDetalleDTO> detalleDTOs = new ArrayList<>();
        double total = 0;
        int cantidadTotal = 0;

        for (DetalleVenta d : detalles) {
            double subtotal = d.getCantidad() * d.getPrecioUnitario();
            detalleDTOs.add(new ProductoDetalleDTO(
                    d.getProducto().getNombre(),
                    d.getCantidad(),
                    d.getPrecioUnitario(),
                    subtotal
            ));
            total += subtotal;
            cantidadTotal += d.getCantidad();
        }

        return new ComprobanteDTO(
        		venta.getId(),
                cliente.getNombre() + " " + cliente.getApellido(),
                new java.sql.Date(venta.getFecha().getTime()),
                cantidadTotal,
                total,
                detalleDTOs
        );
    }
    
    public ByteArrayInputStream generarComprobantePDF(ComprobanteDTO comprobante) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font header = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font normal = new Font(Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("Comprobante de Venta", header));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("ID de Comprobante: " + comprobante.getId(), normal));
            document.add(new Paragraph("Cliente: " + comprobante.getCliente(), normal));
            document.add(new Paragraph("Fecha: " + comprobante.getFecha().toString(), normal));
            document.add(new Paragraph("Total: $" + comprobante.getTotal(), normal));
            document.add(new Paragraph("Cantidad de Productos: " + comprobante.getCantidadTotal(), normal));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4, 1, 2, 2});

            Stream.of("Producto", "Cantidad", "Precio Unitario", "Subtotal")
                    .forEach(headerTitle -> {
                        PdfPCell headerCell = new PdfPCell();
                        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        headerCell.setPhrase(new Phrase(headerTitle));
                        table.addCell(headerCell);
                    });

            for (ProductoDetalleDTO producto : comprobante.getProductos()) {
                table.addCell(producto.getNombreProducto());
                table.addCell(String.valueOf(producto.getCantidad()));
                table.addCell("$" + producto.getPrecioUnitario());
                table.addCell("$" + producto.getSubtotal());
            }

            document.add(table);
            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
