# API REST - Ecommerce de Electrodomésticos

Este proyecto es una **API REST** desarrollada con **Spring Boot** que simula un sistema de ecommerce para un local de electrodomésticos. Permite gestionar clientes, productos y ventas, incluyendo la emisión de comprobantes por cada operación realizada.

---

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Lombok
- Swagger (OpenAPI)
- Maven
- Postman (colecciones de prueba)
- **iText** (para generación de PDF)

---

## Funcionalidad general

El sistema gestiona cuatro recursos principales:

- **Clientes**
- **Productos**
- **Ventas**
- **Comprobantes de venta**

Cada entidad tiene su propia estructura de endpoints y está desacoplada mediante el uso de **DTOs**, lo cual evita exponer relaciones innecesarias.

---

## Endpoints disponibles

### Clientes

- `POST /clientes` – Crear un nuevo cliente.
- `GET /clientes` – Obtener todos los clientes.
- `GET /clientes/{id}` – Obtener un cliente por ID.
- `PUT /clientes/{id}` – Actualizar un cliente.
- `DELETE /clientes/{id}` – Eliminar un cliente.

### Productos

- `POST /productos` – Registrar un nuevo producto.
- `GET /productos` – Obtener todos los productos.
- `GET /productos/{id}` – Obtener un producto por ID.
- `PUT /productos/{id}` – Actualizar un producto.
- `DELETE /productos/{id}` – Eliminar un producto.

### Ventas

- `POST /comprobantes` – Registrar una venta.

### Comprobantes

- `GET ventas/comprobante/{id}` – Obtener un comprobante por su ID.
- `GET ventas/comprobante/{id}/pdf` – Ver detalle de un comprobante por ID.

---

## Generación de comprobante en PDF

El sistema permite al cliente descargar el comprobante de su compra en formato PDF con el endpoint.

Este archivo incluye:
- Datos del cliente
- Lista de productos comprados
- Precios unitarios
- Total de la operación
- Fecha de emisión

## Documentación Swagger

La documentación interactiva de la API se encuentra disponible en:
http://localhost:8080/swagger-ui/index.html

## Manejo de errores

El sistema maneja errores comunes usando un objeto de respuesta `ErrorResponseDTO`, que incluye un mensaje descriptivo y una marca de tiempo. Los errores controlados incluyen:

- `404 Not Found`: recurso no encontrado (cliente, producto, comprobante).
- `500 Internal Server Error`: errores inesperados del sistema.

Ejemplo de respuesta:
```json
{
  "mensaje": "Cliente con ID 12 no encontrado.",
  "timestamp": "2025-05-20T19:32:00"
}
