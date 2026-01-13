package com.yeider.pruebaTecnica.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yeider.pruebaTecnica.exceptions.NotFoundException;
import com.yeider.pruebaTecnica.models.dto.ProductoMayorStockDTO;
import com.yeider.pruebaTecnica.models.entity.Franquicia;
import com.yeider.pruebaTecnica.models.entity.Producto;
import com.yeider.pruebaTecnica.models.entity.Sucursal;
import com.yeider.pruebaTecnica.repository.FranquiciaRepository;

@ExtendWith(MockitoExtension.class)
public class FranquiciaServiceImplTest {

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private FranquiciaServiceImpl franquiciaService;

    private Franquicia franquiciaPrueba;

    @BeforeEach
    public void setUp() {
        franquiciaPrueba = new Franquicia();
        franquiciaPrueba.setId(1L);
        franquiciaPrueba.setNombre("Franquicia Master");
    }

    @Test
    public void crear_DebeGuardarYRetornarFranquicia() {
        when(franquiciaRepository.save(any(Franquicia.class))).thenReturn(franquiciaPrueba);

        Franquicia resultado = franquiciaService.crear(new Franquicia());

        assertNotNull(resultado);
        assertEquals("Franquicia Master", resultado.getNombre());
        verify(franquiciaRepository, times(1)).save(any());
    }

    @Test
    public void actualizarNombre_DebeCambiarNombreCuandoExiste() {
        when(franquiciaRepository.findById(1L)).thenReturn(Optional.of(franquiciaPrueba));
        when(franquiciaRepository.save(any(Franquicia.class))).thenReturn(franquiciaPrueba);

        Franquicia actualizada = franquiciaService.actualizarNombre(1L, "Nuevo Nombre");

        assertEquals("Nuevo Nombre", actualizada.getNombre());
        verify(franquiciaRepository).save(franquiciaPrueba);
    }

    @Test
    public void actualizarNombre_DebeLanzarExcepcionCuandoNoExiste() {
        when(franquiciaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            franquiciaService.actualizarNombre(1L, "Error");
        });
    }

    @Test
    public void obtenerProductoMayorStockPorSucursal_DebeRetornarListaDTO() {
        Sucursal s1 = new Sucursal();
        s1.setNombre("Sucursal Norte");
        
        Producto p1 = new Producto(); p1.setNombre("Producto A"); p1.setStock(10);
        Producto p2 = new Producto(); p2.setNombre("Producto B"); p2.setStock(50);
        s1.setProductos(Arrays.asList(p1, p2));

        franquiciaPrueba.setSucursales(Collections.singletonList(s1));

        when(franquiciaRepository.findById(1L)).thenReturn(Optional.of(franquiciaPrueba));

        List<ProductoMayorStockDTO> resultado = franquiciaService.obtenerProductoMayorStockPorSucursal(1L);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Producto B", resultado.get(0).getProducto());
        assertEquals(50, resultado.get(0).getStock());
        assertEquals("Sucursal Norte", resultado.get(0).getSucursal());
    }
}