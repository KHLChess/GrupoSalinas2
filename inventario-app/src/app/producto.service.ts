import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from './producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlBase = "http://localhost:8080/inventario-app/productos";

  constructor(private clienteHttp : HttpClient) { }

  getProductos() : Observable<Producto[]>{
    return this.clienteHttp.get<Producto[]>(this.urlBase);
  }

  setProductos(producto : Producto) : Observable<Object>{
    return this.clienteHttp.post(this.urlBase, producto);
  }

  getProductoById(idProducto : number){
    return this.clienteHttp.get<Producto>(`${this.urlBase}/${idProducto}`)
  }

  updateProducto(idProducto : number, producto : Producto) : Observable<Object>{
    return this.clienteHttp.put(`${this.urlBase}/${idProducto}`, producto);
  }

  deleteProducto(idProducto : number) : Observable<Object>{
    return this.clienteHttp.delete(`${this.urlBase}/${idProducto}`);
  }
}
