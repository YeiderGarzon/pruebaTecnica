package com.yeider.pruebaTecnica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeider.pruebaTecnica.models.dto.ActualizarNombreRequest;
import com.yeider.pruebaTecnica.models.dto.CrearFranquiciaRequest;
import com.yeider.pruebaTecnica.models.dto.CrearSucursalRequest;
import com.yeider.pruebaTecnica.models.dto.ProductoMayorStockDTO;
import com.yeider.pruebaTecnica.models.entity.Franquicia;
import com.yeider.pruebaTecnica.models.entity.Sucursal;
import com.yeider.pruebaTecnica.service.FranquiciaService;
import com.yeider.pruebaTecnica.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/franquicias")
@Tag(name = "Franquicias", description = "Gestión de franquicias y sus sucursales")
public class FranquiciaController {

    private final FranquiciaService franquiciaService;
    private final SucursalService sucursalService;

    public FranquiciaController(FranquiciaService franquiciaService, SucursalService sucursalService) {
        this.franquiciaService = franquiciaService;
        this.sucursalService = sucursalService;
    }

    @Operation(summary = "Crear nueva franquicia", description = "Crea una nueva franquicia proporcionando su nombre")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Franquicia creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public Franquicia crear(@RequestBody CrearFranquiciaRequest request) {
        Franquicia franquicia = new Franquicia();
        franquicia.setNombre(request.getNombre());
        return franquiciaService.crear(franquicia);
    }

    @Operation(summary = "Listar franquicias", description = "Obtiene el listado de todas las franquicias registradas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public List<Franquicia> listar() {
        return franquiciaService.listar();
    }

    @Operation(summary = "Agregar sucursal a franquicia", description = "Agrega una nueva sucursal a una franquicia existente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sucursal agregada correctamente"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @PostMapping("/{franquiciaId}/sucursales")
    public Sucursal agregarSucursal(
            @Parameter(description = "ID de la franquicia", example = "1")
            @PathVariable Long franquiciaId,
            @RequestBody CrearSucursalRequest request) {

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(request.getNombre());
        return sucursalService.agregarSucursal(franquiciaId, sucursal);
    }

    @Operation(summary = "Obtener producto con mayor stock por sucursal", description = "Devuelve el producto con mayor stock por cada sucursal de una franquicia específica")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @GetMapping("/{franquiciaId}/productos-mayor-stock")
    public List<ProductoMayorStockDTO> obtenerMayorStock(
            @Parameter(description = "ID de la franquicia", example = "1")
            @PathVariable Long franquiciaId) {
        return franquiciaService.obtenerProductoMayorStockPorSucursal(franquiciaId);
    }

    @Operation(summary = "Actualizar nombre de franquicia", description = "Actualiza el nombre de una franquicia existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Nombre actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @PutMapping("/{id}/nombre")
    public Franquicia actualizarNombre(
            @Parameter(description = "ID de la franquicia", example = "1")
            @PathVariable Long id,
            @RequestBody ActualizarNombreRequest request) {

        return Optional.ofNullable(request.getNombre())
                .map(nombre -> franquiciaService.actualizarNombre(id, nombre))
                .orElseThrow(() -> new IllegalArgumentException("El nombre es obligatorio"));
    }

}
