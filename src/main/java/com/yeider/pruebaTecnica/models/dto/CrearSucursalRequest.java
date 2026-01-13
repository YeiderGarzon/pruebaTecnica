package com.yeider.pruebaTecnica.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Request para crear una sucursal")
@Getter
@Setter
public class CrearSucursalRequest {

    @Schema(description = "Nombre de la sucursal", example = "Sucursal Centro")
    private String nombre;

}
