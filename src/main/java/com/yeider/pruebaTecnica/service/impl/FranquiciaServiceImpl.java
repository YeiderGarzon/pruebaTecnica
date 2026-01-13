package com.yeider.pruebaTecnica.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yeider.pruebaTecnica.exceptions.NotFoundException;
import com.yeider.pruebaTecnica.models.dto.ProductoMayorStockDTO;
import com.yeider.pruebaTecnica.models.entity.Franquicia;
import com.yeider.pruebaTecnica.models.entity.Producto;
import com.yeider.pruebaTecnica.repository.FranquiciaRepository;
import com.yeider.pruebaTecnica.service.FranquiciaService;

@Service
public class FranquiciaServiceImpl implements FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;

    public FranquiciaServiceImpl(FranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }

    @Override
    public Franquicia crear(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    @Override
    public List<Franquicia> listar() {
        return franquiciaRepository.findAll();
    }
    
    @Override
    public List<ProductoMayorStockDTO> obtenerProductoMayorStockPorSucursal(Long franquiciaId) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
            .orElseThrow(() -> new NotFoundException("Franquicia no encontrada"));

        return franquicia.getSucursales()
            .stream()
            .flatMap(sucursal ->
                sucursal.getProductos()
                    .stream()
                    .max(Comparator.comparingInt(Producto::getStock))
                    .stream()
                    .map(producto -> {
                        ProductoMayorStockDTO dto = new ProductoMayorStockDTO();
                        dto.setSucursal(sucursal.getNombre());
                        dto.setProducto(producto.getNombre());
                        dto.setStock(producto.getStock());
                        return dto;
                    })
            )
            .toList();
    }
    
    @Override
    public Franquicia actualizarNombre(Long id, String nuevoNombre) {
        return franquiciaRepository.findById(id)
        	.map(franquicia -> {
        		franquicia.setNombre(nuevoNombre);
        		return franquicia;
        	})
        	.map(franquiciaRepository::save)
            .orElseThrow(() -> new NotFoundException("No encontrada"));
    }

}

