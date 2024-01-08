import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-producto-lista',
  standalone: true,
  imports: [],
  templateUrl: './producto-lista.component.html'
})
export class ProductoListaComponent {
  productos : Producto[];

  constructor(private productoServicio : ProductoService, private enrutador : Router){

  }

  ngOnInit(){
    this.getProductos();
  }

  private getProductos(){
    this.productoServicio.getProductos().subscribe(
      (datos => {
        this.productos = datos;
      })
    );
  }

  updateProducto(idProducto : number){
    this.enrutador.navigate(['editar-producto', idProducto]);
  }

  deleteProducto(idProducto : number){
    this.productoServicio.deleteProducto(idProducto).subscribe({
      next: (datos) => this.getProductos(),
      error: (error) => console.log(error)
    });
  }
  
}
