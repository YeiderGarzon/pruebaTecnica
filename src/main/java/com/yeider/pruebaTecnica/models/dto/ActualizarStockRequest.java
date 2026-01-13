package com.yeider.pruebaTecnica.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Request para actualizar un stock")
@Getter
@Setter
public class ActualizarStockRequest {
	@Schema(description = "Nuevo stock a actualizar", example = "150")
    private Integer stock;
}

