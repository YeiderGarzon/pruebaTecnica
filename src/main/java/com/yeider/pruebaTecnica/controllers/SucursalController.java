package com.yeider.pruebaTecnica.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeider.pruebaTecnica.models.dto.ActualizarNombreRequest;
import com.yeider.pruebaTecnica.models.dto.CrearProductoRequest;
import com.yeider.pruebaTecnica.models.entity.Producto;
import com.yeider.pruebaTecnica.models.entity.Sucursal;
import com.yeider.pruebaTecnica.service.ProductoService;
import com.yeider.pruebaTecnica.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursales", description = "Gestión de sucursales y sus productos")
public class SucursalController {

    private final ProductoService productoService;
    private final SucursalService sucursalService;

    public SucursalController(ProductoService productoService, SucursalService sucursalService) {
        this.productoService = productoService;
        this.sucursalService = sucursalService;
    }

    @Operation(
        summary = "Agregar producto a sucursal",
        description = "Agrega un nuevo producto a una sucursal específica"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto agregado correctamente"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PostMapping("/{sucursalId}/productos")
    public Producto agregarProducto(
            @Parameter(description = "ID de la sucursal", example = "5")
            @PathVariable Long sucursalId,
            @RequestBody CrearProductoRequest request) {

        return Optional.of(request)
                .map(r -> {
                    Producto p = new Producto();
                    p.setNombre(r.getNombre());
                    p.setStock(r.getStock());
                    return p;
                })
                .map(p -> productoService.agregarProducto(sucursalId, p))
                .orElseThrow();
    }

    @Operation(
        summary = "Actualizar nombre de sucursal",
        description = "Actualiza el nombre de una sucursal existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nombre actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}/nombre")
    public Sucursal actualizarNombre(
            @Parameter(description = "ID de la sucursal", example = "5")
            @PathVariable Long id,
            @RequestBody ActualizarNombreRequest request) {

        return Optional.of(request.getNombre())
                .map(nombre -> sucursalService.actualizarNombre(id, nombre))
                .orElseThrow();
    }
}