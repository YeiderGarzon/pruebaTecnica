package com.yeider.pruebaTecnica.service;

import java.util.List;

import com.yeider.pruebaTecnica.models.dto.ProductoMayorStockDTO;
import com.yeider.pruebaTecnica.models.entity.Franquicia;

public interface FranquiciaService {
    Franquicia crear(Franquicia franquicia);
    List<Franquicia> listar();
	List<ProductoMayorStockDTO> obtenerProductoMayorStockPorSucursal(Long franquiciaId);
	Franquicia actualizarNombre(Long id, String string);
}

