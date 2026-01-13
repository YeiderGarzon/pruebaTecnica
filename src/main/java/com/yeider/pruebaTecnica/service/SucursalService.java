package com.yeider.pruebaTecnica.service;

import com.yeider.pruebaTecnica.models.entity.Sucursal;

public interface SucursalService {
	
	Sucursal agregarSucursal(Long franquiciaId, Sucursal sucursal);

	Sucursal actualizarNombre(Long id, String string);
	
}
