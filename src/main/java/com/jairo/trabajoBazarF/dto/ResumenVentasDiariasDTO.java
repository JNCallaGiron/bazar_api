package com.jairo.trabajoBazarF.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumenVentasDiariasDTO {
    private LocalDate fecha;
    private int cantidadVentas;
    private double totalFacturado;
    private List<Long> codigosVentas; // IDs de las ventas realizadas ese día
}

