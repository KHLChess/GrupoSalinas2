package com.examenpractico.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenpractico.exception.NotFountExceptionProducto;
import com.examenpractico.modelo.Producto;
import com.examenpractico.service.ProductoService;

@RestController
//https://localhost:8080/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")

public class ProductoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	//http://localhost:8080/inventario-app/productos
	@GetMapping("/productos")
	public List<Producto> getProductos(){
		List<Producto> productos = this.productoService.listaProductos();
		logger.info("Productos obtenidos: ");
		productos.forEach((producto -> logger.info(producto.toString())));
		return productos;
	}
	
	@PostMapping("/productos")
	public Producto setProducto(@RequestBody Producto producto) {
		logger.info("Productos a Agregar: " + producto);
		return this.productoService.guardaProducto(producto);
	}
	
	@GetMapping("/productos/{idProducto}")
	public ResponseEntity<Producto> getProductoById(@PathVariable int idProducto){
		Producto producto = this.productoService.buscaProductoId(idProducto);
		if(producto != null) {
			return ResponseEntity.ok(producto);
		}else {
			throw new NotFountExceptionProducto("Producto Inexistente");
		}
	}
	
	@PutMapping("/productos/{idProducto}")
	public ResponseEntity<Producto> updateProducto(@PathVariable int idProducto, 
												   @RequestBody Producto recibeProducto){
		Producto producto = this.productoService.buscaProductoId(idProducto);
		if(producto == null) {
			throw new NotFountExceptionProducto("Producto Inexistente con el id: " + idProducto);
		}
		producto.setDescripcion(recibeProducto.getDescripcion());
		producto.setPrecio(recibeProducto.getPrecio());
		producto.setExistencia(recibeProducto.getExistencia());
		this.productoService.guardaProducto(producto);
		return ResponseEntity.ok(producto);
	}
	
	@DeleteMapping("/productos/{idProducto}")
	public ResponseEntity<Map<String, Boolean>> deleteproducto(@PathVariable int idProducto){
		Producto producto =  productoService.buscaProductoId(idProducto);
		if(producto == null) {
			throw new NotFountExceptionProducto("Producto Inexistente con el id: " + idProducto);
		}
		this.productoService.eliminarProductoId(producto.getIdProducto());
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
