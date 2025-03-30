package com.jairo.trabajoBazarF.service;

import com.jairo.trabajoBazarF.model.Cliente;
import com.jairo.trabajoBazarF.model.Venta;
import com.jairo.trabajoBazarF.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clientesRepository;


    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public List<Cliente> getClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Optional<Cliente> findCliente(Long id) {
        return clientesRepository.findById(id);
    }

    @Override
    public Cliente editCliente(Long id, Cliente clienteEditado) {
        Cliente cli = this.findCliente(id).orElse(null);
        if(cli != null){
            cli.setNombre(clienteEditado.getNombre());
            cli.setApellido(clienteEditado.getApellido());
            cli.setDni(clienteEditado.getDni());

            this.saveCliente(cli);
            return cli;
        }
        return  null;
    }

    @Override
    public boolean deleteCliente(Long id) {
        if (clientesRepository.existsById(id)){
            clientesRepository.deleteById(id);
            return true;
        }
        return  false;
    }

    @Override
    public List<Venta> getVentaCliente(Long idCliente) {
        Cliente cli = clientesRepository.findById(idCliente).orElse(null);
        if (cli!=null){
            return cli.getVentas();
        }return  null;
    }
}
