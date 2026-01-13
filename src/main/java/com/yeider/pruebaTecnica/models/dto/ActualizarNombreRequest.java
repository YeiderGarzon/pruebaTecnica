package com.yeider.pruebaTecnica.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Request para actualizar un nombre")
@Getter
@Setter
public class ActualizarNombreRequest {

    @Schema(description = "Nuevo nombre a actualizar", example = "Nuevo nombre")
    private String nombre;

}
