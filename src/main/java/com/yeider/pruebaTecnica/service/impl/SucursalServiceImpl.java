package com.yeider.pruebaTecnica.service.impl;

import org.springframework.stereotype.Service;

import com.yeider.pruebaTecnica.exceptions.NotFoundException;
import com.yeider.pruebaTecnica.models.entity.Sucursal;
import com.yeider.pruebaTecnica.repository.FranquiciaRepository;
import com.yeider.pruebaTecnica.repository.SucursalRepository;
import com.yeider.pruebaTecnica.service.SucursalService;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository,
                               FranquiciaRepository franquiciaRepository) {
        this.sucursalRepository = sucursalRepository;
        this.franquiciaRepository = franquiciaRepository;
    }

    @Override
    public Sucursal agregarSucursal(Long franquiciaId, Sucursal sucursal) {
        return franquiciaRepository.findById(franquiciaId)
                .map(franquicia -> {
                    sucursal.setFranquicia(franquicia);
                    return sucursalRepository.save(sucursal);
                })
                .orElseThrow(() -> new NotFoundException("Franquicia no encontrada con id: " + franquiciaId));
    }

    @Override
    public Sucursal actualizarNombre(Long id, String nuevoNombre) {
        return sucursalRepository.findById(id)
                .map(sucursal -> {
                    sucursal.setNombre(nuevoNombre);
                    return sucursal;
                })
                .map(sucursalRepository::save)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada con id: " + id));
    }
}
    