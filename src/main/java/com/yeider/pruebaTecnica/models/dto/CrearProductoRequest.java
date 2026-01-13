package com.yeider.pruebaTecnica.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Request para crear un producto")
@Getter
@Setter
public class CrearProductoRequest {

    @Schema(description = "Nombre del producto", example = "Amper Blanco")
    private String nombre;
    
    @Schema(description = "Cantidad de stock del producto", example = "100")
    private int stock;

}
