package com.yeider.pruebaTecnica.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yeider.pruebaTecnica.exceptions.NotFoundException;
import com.yeider.pruebaTecnica.models.entity.Franquicia;
import com.yeider.pruebaTecnica.models.entity.Sucursal;
import com.yeider.pruebaTecnica.repository.FranquiciaRepository;
import com.yeider.pruebaTecnica.repository.SucursalRepository;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceImplTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;

    private Franquicia franquiciaPrueba;
    private Sucursal sucursalPrueba;

    @BeforeEach
    public void setUp() {
        franquiciaPrueba = new Franquicia();
        franquiciaPrueba.setId(1L);
        franquiciaPrueba.setNombre("Franquicia Colombia");

        sucursalPrueba = new Sucursal();
        sucursalPrueba.setId(100L);
        sucursalPrueba.setNombre("Sucursal Medellin");
        sucursalPrueba.setFranquicia(franquiciaPrueba);
    }

    @Test
    @DisplayName("Debe agregar una sucursal correctamente a una franquicia")
    public void agregarSucursal_Exito() {
        when(franquiciaRepository.findById(1L)).thenReturn(Optional.of(franquiciaPrueba));
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalPrueba);

        Sucursal resultado = sucursalService.agregarSucursal(1L, new Sucursal());

        assertNotNull(resultado);
        assertEquals("Sucursal Medellin", resultado.getNombre());
        assertEquals(1L, resultado.getFranquicia().getId());
        verify(franquiciaRepository).findById(1L);
        verify(sucursalRepository).save(any(Sucursal.class));
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException si la franquicia no existe al agregar")
    public void agregarSucursal_FranquiciaNoEncontrada() {
        when(franquiciaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            sucursalService.agregarSucursal(999L, new Sucursal());
        });
        verify(sucursalRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe actualizar el nombre de la sucursal correctamente")
    public void actualizarNombre_Exito() {
        when(sucursalRepository.findById(100L)).thenReturn(Optional.of(sucursalPrueba));
        when(sucursalRepository.save(any(Sucursal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Sucursal resultado = sucursalService.actualizarNombre(100L, "Sucursal Bogota");

        assertEquals("Sucursal Bogota", resultado.getNombre());
        verify(sucursalRepository).save(sucursalPrueba);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException si la sucursal no existe al actualizar")
    public void actualizarNombre_NoEncontrada() {
        when(sucursalRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            sucursalService.actualizarNombre(100L, "Nuevo Nombre");
        });
        verify(sucursalRepository, never()).save(any());
    }
}