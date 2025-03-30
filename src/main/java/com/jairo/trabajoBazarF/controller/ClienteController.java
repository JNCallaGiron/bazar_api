package com.jairo.trabajoBazarF.controller;

import com.jairo.trabajoBazarF.model.Cliente;
import com.jairo.trabajoBazarF.model.Venta;
import com.jairo.trabajoBazarF.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
        Cliente cli =clienteService.saveCliente(cliente);
        return  ResponseEntity.ok(cli);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes (){
        List<Cliente> lista = clienteService.getClientes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> findCliente(@PathVariable Long id_cliente){
        Optional<Cliente> cli = clienteService.findCliente(id_cliente);
        return cli.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id_cliente){
        boolean clienteBorrado= clienteService.deleteCliente(id_cliente);
        if (clienteBorrado){
            return ResponseEntity.ok("Cliente eliminado correctamente");
        }return ResponseEntity.notFound().build();
    }

    @PutMapping("/editar/{id_cliente}")
    public ResponseEntity<Cliente> editCliente(@PathVariable Long id_cliente,
                                               @RequestBody Cliente clienteEditado){
        Cliente cli = clienteService.editCliente(id_cliente, clienteEditado);
        if( cli != null){
            return ResponseEntity.ok(cli);
        }return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    //ventas de un cliente
    @GetMapping("/ventas/{idCliente}")
    public ResponseEntity<List<Venta>> getVentasPorCliente(@PathVariable Long idCliente) {
        List<Venta> ventas = clienteService.getVentaCliente(idCliente);
        if (ventas != null) {
            return ResponseEntity.ok(ventas);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
