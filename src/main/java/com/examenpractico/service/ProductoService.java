package com.examenpractico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenpractico.modelo.Producto;
import com.examenpractico.repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Producto> listaProductos() {
		return this.productoRepository.findAll();
	}

	@Override
	public Producto buscaProductoId(Integer idProducto) {
		Producto producto = this.productoRepository
				.findById(idProducto)
				.orElse(null);
		return producto;
	}

	@Override
	public Producto guardaProducto(Producto producto) {
		// TODO Auto-generated method stub
		return this.productoRepository.save(producto);
		
	}

	@Override
	public void eliminarProductoId(Integer idProducto) {
		// TODO Auto-generated method stub
		this.productoRepository.deleteById(idProducto);
	}

}
