package com.jairo.trabajoBazarF.controller;

import com.jairo.trabajoBazarF.model.Producto;
import com.jairo.trabajoBazarF.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<String> saveProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.saveProducto(producto);
        return ResponseEntity.ok("Producto creado con éxito: " + nuevoProducto.getNombre());
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos(){
        List<Producto> lista = productoService.getProductos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<Producto> findProducto(@PathVariable Long codigo_producto){
        Optional<Producto> pro = productoService.findProducto(codigo_producto);
        return pro.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long codigo_producto){
        boolean borrado = productoService.deleteProducto(codigo_producto);
        if (borrado){
            return  ResponseEntity.ok("producto eliminado correctamente");
        }return ResponseEntity.status(404).body("Producto no encontrado");
    }

    @PutMapping("/editar/{codigo_producto}")
    public ResponseEntity<Producto> editProducto(@PathVariable Long codigo_producto,
                                                 @RequestBody Producto productoEditado  ){
        Producto pro = productoService.editProducto(codigo_producto, productoEditado);
        if( pro != null){
            return ResponseEntity.ok(pro);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
