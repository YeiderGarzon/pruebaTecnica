package com.yeider.pruebaTecnica.service.impl;

import org.springframework.stereotype.Service;

import com.yeider.pruebaTecnica.exceptions.NotFoundException;
import com.yeider.pruebaTecnica.models.entity.Producto;
import com.yeider.pruebaTecnica.repository.ProductoRepository;
import com.yeider.pruebaTecnica.repository.SucursalRepository;
import com.yeider.pruebaTecnica.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, SucursalRepository sucursalRepository) {
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public Producto agregarProducto(Long sucursalId, Producto producto) {
        return sucursalRepository.findById(sucursalId)
                .map(sucursal -> {
                    producto.setSucursal(sucursal);
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));
    }

    @Override
    public void eliminar(Long productoId) {
        productoRepository.findById(productoId)
                .ifPresentOrElse(
                        productoRepository::delete,
                        () -> { throw new NotFoundException("Producto no encontrado con id: " + productoId); }
                );
    }

    @Override
    public Producto actualizarStock(Long productoId, int nuevoStock) {
        return productoRepository.findById(productoId)
                .map(producto -> {
                    producto.setStock(nuevoStock);
                    return producto;
                })
                .map(productoRepository::save)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + productoId));
    }


    @Override
    public Producto actualizarNombre(Long id, String nuevoNombre) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(nuevoNombre);
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + id));
    }
}