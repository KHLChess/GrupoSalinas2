package com.examenpractico.service;

import java.util.List;

import com.examenpractico.modelo.Producto;

public interface IProductoService {
	
	public List<Producto> listaProductos();
	
	public Producto buscaProductoId(Integer idProducto);
	
	public Producto guardaProducto(Producto producto);
	
	public void eliminarProductoId(Integer idProducto);
}
