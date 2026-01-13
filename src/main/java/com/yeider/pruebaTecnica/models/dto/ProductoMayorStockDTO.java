package com.yeider.pruebaTecnica.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoMayorStockDTO {
    private String sucursal;
    private String producto;
    private int stock;
}

