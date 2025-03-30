package com.jairo.trabajoBazarF.service;

import com.jairo.trabajoBazarF.model.Producto;
import com.jairo.trabajoBazarF.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements  IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findProducto(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto editProducto(Long id, Producto productoEditado) {
        Producto pro = this.findProducto(id).orElse(null);
        if( pro != null) {
            pro.setNombre(productoEditado.getNombre());
            pro.setMarca(productoEditado.getMarca());
            pro.setCosto(productoEditado.getCosto());
            pro.setCantidadDisponible(productoEditado.getCantidadDisponible());

            this.saveProducto(pro);
            return pro;
        }return null;
    }

    @Override
    public boolean deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }return false;
    }
}
