import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'console';

@Component({
  selector: 'app-editar-producto',
  standalone: true,
  imports: [],
  templateUrl: './editar-producto.component.html'
})
export class EditarProductoComponent {
  producto : Producto = new Producto();
  idProducto : number;

  constructor(private productoServicio : ProductoService, 
              private ruta : ActivatedRoute, 
              private enrutador : Router){}

  ngOnInit(){
    this.idProducto = this.ruta.snapshot.params['idProducto'];
    this.productoServicio.getProductoById(this.idProducto).subscribe({
      next: (datos) => this.producto = datos
      ,
      error: (error:any) => console.log(error) 
    });
  }

  onSubmit(){
    this.updateProducto();
  }

  updateProducto(){
    this.productoServicio.updateProducto(this.idProducto, this.producto).subscribe({
      next: (datos) => this.irProductoLista(),
      error: (error) => console.log(error)
    });
  }

  irProductoLista(){
    this.enrutador.navigate(['/productos']);
  }
}
