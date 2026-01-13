package com.yeider.pruebaTecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yeider.pruebaTecnica.models.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
