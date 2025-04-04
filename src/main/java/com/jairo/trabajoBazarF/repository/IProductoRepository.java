package com.jairo.trabajoBazarF.repository;

import com.jairo.trabajoBazarF.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository  extends JpaRepository<Producto, Long> {
}
