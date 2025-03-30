package com.jairo.trabajoBazarF.service;

import com.jairo.trabajoBazarF.dto.ResumenVentasDiariasDTO;
import com.jairo.trabajoBazarF.model.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVentaService {
    Venta saveVenta(Venta venta);
    List<Venta> getVentas();
    Optional<Venta> findVenta(Long id);
    Venta editVenta(Long id, Venta ventaEditada);
    boolean deleteVenta(Long id);
    //venta del dia
    ResumenVentasDiariasDTO obtenerResumenDeVentasPorFecha(LocalDate fecha);
}
