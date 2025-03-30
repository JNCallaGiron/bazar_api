package com.jairo.trabajoBazarF.controller;

import com.jairo.trabajoBazarF.dto.ResumenVentasDiariasDTO;
import com.jairo.trabajoBazarF.model.Venta;
import com.jairo.trabajoBazarF.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @PostMapping("/crear")
    public ResponseEntity<?> saveVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.saveVenta(venta);
        if (nuevaVenta == null) {
            return ResponseEntity.badRequest().body("No se pudo registrar la venta. Cliente, producto o stock inválido.");
        }
        return ResponseEntity.ok("Venta creada correctamente");
    }


    @GetMapping("/lista")
    public ResponseEntity<List<Venta>> getVentas(){
        List<Venta> lista = ventaService.getVentas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{codigoVenta}")
    public ResponseEntity<Venta> findVenta(@PathVariable Long codigoVenta){
        Optional<Venta> venta= ventaService.findVenta(codigoVenta);
        return venta.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{codigoVenta}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long codigoVenta){
        boolean ventaELiminada = ventaService.deleteVenta(codigoVenta);
        if(ventaELiminada){
            return ResponseEntity.ok("Venta eliminada correctamente");
        }return  ResponseEntity.badRequest().build();
    }


    @PutMapping("/editar/{codigoVenta}")
    public ResponseEntity<Venta> editVenta(@PathVariable Long codigoVenta,
                                           @RequestBody Venta ventaEditada){
        Venta ven = ventaService.editVenta(codigoVenta,ventaEditada);
        if (ven != null){
            return ResponseEntity.ok(ven);
        }return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    //ventas de un dia determinado
    @GetMapping("/resumen/{fecha}")
    public ResponseEntity<?> getResumenVentasPorFecha(@PathVariable String fecha) {
        LocalDate fechaParseada;
        try {
            fechaParseada = LocalDate.parse(fecha); // formato: "2025-03-30"
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de fecha inválido. Usa yyyy-MM-dd");
        }

        ResumenVentasDiariasDTO dto = ventaService.obtenerResumenDeVentasPorFecha(fechaParseada);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ventas para la fecha: " + fecha);
        }
    }

}
