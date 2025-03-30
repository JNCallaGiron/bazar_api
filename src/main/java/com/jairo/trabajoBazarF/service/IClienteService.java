package com.jairo.trabajoBazarF.service;


import com.jairo.trabajoBazarF.model.Cliente;
import com.jairo.trabajoBazarF.model.Venta;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    //create
    Cliente saveCliente (Cliente cliente);
    //read
    List<Cliente> getClientes();
    Optional<Cliente> findCliente(Long  id);
    //update
    Cliente editCliente(Long id, Cliente clienteEditado);
    //delete
    boolean deleteCliente(Long id);
    //lista de ventas de un cliente
    List<Venta> getVentaCliente(Long idCliente);
}
