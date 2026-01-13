package com.yeider.pruebaTecnica.service;

import com.yeider.pruebaTecnica.models.entity.Producto;

public interface ProductoService {
	
	Producto agregarProducto(Long sucursalId, Producto producto);

	void eliminar(Long productoId);

	Producto actualizarStock(Long productoId, int nuevoStock);

	Producto actualizarNombre(Long id, String nuevoNombre);

}
