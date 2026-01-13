package com.yeider.pruebaTecnica.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Request para crear una franquicia")
@Getter
@Setter
public class CrearFranquiciaRequest {

    @Schema(description = "Nombre de la franquicia", example = "Franquicia Norte")
    private String nombre;

}
