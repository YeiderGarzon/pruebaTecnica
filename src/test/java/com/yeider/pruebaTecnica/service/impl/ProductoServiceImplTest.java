package com.yeider.pruebaTecnica.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yeider.pruebaTecnica.exceptions.NotFoundException;
import com.yeider.pruebaTecnica.models.entity.Producto;
import com.yeider.pruebaTecnica.models.entity.Sucursal;
import com.yeider.pruebaTecnica.repository.ProductoRepository;
import com.yeider.pruebaTecnica.repository.SucursalRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto productoPrueba;
    private Sucursal sucursalPrueba;

    @BeforeEach
    public void setUp() {
        sucursalPrueba = new Sucursal();
        sucursalPrueba.setId(10L);
        sucursalPrueba.setNombre("Sucursal Centro");

        productoPrueba = new Producto();
        productoPrueba.setId(1L);
        productoPrueba.setNombre("Laptop");
        productoPrueba.setStock(5);
        productoPrueba.setSucursal(sucursalPrueba);
    }

    @Test
    @DisplayName("Debe agregar un producto correctamente a una sucursal existente")
    public void agregarProducto_Exito() {
        when(sucursalRepository.findById(10L)).thenReturn(Optional.of(sucursalPrueba));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoPrueba);

        Producto resultado = productoService.agregarProducto(10L, new Producto());

        assertNotNull(resultado);
        assertEquals("Laptop", resultado.getNombre());
        assertEquals(10L, resultado.getSucursal().getId());
        verify(sucursalRepository).findById(10L);
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al agregar producto en sucursal inexistente")
    public void agregarProducto_SucursalNoEncontrada() {
        when(sucursalRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            productoService.agregarProducto(99L, new Producto());
        });
        verify(productoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe eliminar un producto si existe")
    public void eliminar_Exito() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoPrueba));
        doNothing().when(productoRepository).delete(productoPrueba);

        assertDoesNotThrow(() -> productoService.eliminar(1L));
        verify(productoRepository).delete(productoPrueba);
    }

    @Test
    @DisplayName("Debe actualizar el stock de un producto existente")
    public void actualizarStock_Exito() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoPrueba));
        when(productoRepository.save(any(Producto.class))).thenAnswer(i -> i.getArguments()[0]);

        Producto resultado = productoService.actualizarStock(1L, 100);

        assertEquals(100, resultado.getStock());
        verify(productoRepository).save(productoPrueba);
    }

    @Test
    @DisplayName("Debe actualizar el nombre de un producto")
    public void actualizarNombre_Exito() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoPrueba));
        when(productoRepository.save(any(Producto.class))).thenAnswer(i -> i.getArguments()[0]);

        Producto resultado = productoService.actualizarNombre(1L, "Nuevo Nombre");

        assertEquals("Nuevo Nombre", resultado.getNombre());
        verify(productoRepository).save(any());
    }
}