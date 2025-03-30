package com.jairo.trabajoBazarF.service;

import com.jairo.trabajoBazarF.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    //create
    Producto saveProducto (Producto producto);
    //read
    List<Producto> getProductos();
    Optional<Producto> findProducto(Long  id);
    //update
    Producto editProducto(Long id, Producto productoEditado);
    //delete
    boolean deleteProducto(Long id);

}
