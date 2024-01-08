import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-producto',
  standalone: true,
  imports: [],
  templateUrl: './agregar-producto.component.html',
  styleUrl: './agregar-producto.component.css'
})
export class AgregarProductoComponent {
  producto : Producto = new Producto();
  productoServicio: ProductoService;

  constructor(productoServicio : ProductoService, private enrutador : Router){}

  onSubmit(){
    this.setProducto();
  }

  setProducto(){
    this.productoServicio.setProductos(this.producto).subscribe({
      next : (datos) => {
        this.irListaProductos();
      },
      error: (error: any) => {console.log(error)}
    }); 
  }

  irListaProductos(){
    this.enrutador.navigate(['/productos'])
  }
}
