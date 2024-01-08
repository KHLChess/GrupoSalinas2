package com.examenpractico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examenpractico.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
