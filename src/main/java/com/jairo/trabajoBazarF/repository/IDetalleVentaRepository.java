package com.jairo.trabajoBazarF.repository;

import com.jairo.trabajoBazarF.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
