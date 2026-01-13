package com.yeider.pruebaTecnica.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeider.pruebaTecnica.models.dto.ActualizarNombreRequest;
import com.yeider.pruebaTecnica.models.dto.ActualizarStockRequest;
import com.yeider.pruebaTecnica.models.entity.Producto;
import com.yeider.pruebaTecnica.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Gestión de productos por sucursal")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(
        summary = "Eliminar producto",
        description = "Elimina un producto existente por su ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{productoId}")
    public void eliminarProducto(
            @Parameter(description = "ID del producto a eliminar", example = "10")
            @PathVariable Long productoId) {

        productoService.eliminar(productoId);
    }

    @Operation(
        summary = "Actualizar stock de producto",
        description = "Actualiza la cantidad de stock disponible de un producto"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "400", description = "Stock inválido")
    })
    @PutMapping("/{productoId}/stock")
    public Producto actualizarStock(
            @Parameter(description = "ID del producto", example = "10")
            @PathVariable Long productoId,
            @RequestBody ActualizarStockRequest request) {

        return Optional.ofNullable(request.getStock())
                .filter(stock -> stock >= 0)
                .map(stock -> productoService.actualizarStock(productoId, stock))
                .orElseThrow(() -> new IllegalArgumentException("El stock es obligatorio y debe ser mayor o igual a 0"));
    }

    @Operation(
        summary = "Actualizar nombre de producto",
        description = "Actualiza el nombre de un producto existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nombre actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "400", description = "Nombre inválido")
    })
    @PutMapping("/{id}/nombre")
    public Producto actualizarNombre(
            @Parameter(description = "ID del producto", example = "10")
            @PathVariable Long id,
            @RequestBody ActualizarNombreRequest request) {

        return Optional.ofNullable(request.getNombre())
                .filter(nombre -> !nombre.isBlank())
                .map(nombre -> productoService.actualizarNombre(id, nombre))
                .orElseThrow(() -> new IllegalArgumentException("El nombre es obligatorio"));
    }
}
