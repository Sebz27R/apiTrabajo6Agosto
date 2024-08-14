package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepo clienteRepo;

    public List<Cliente> getAllClientes() {
        return clienteRepo.findAll();
    }

    public Optional<Cliente> getClienteById(int id) {
        return clienteRepo.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public Cliente updateCliente(int id, Cliente clienteDetails) {
        Cliente clienteActualizado = clienteRepo.findById(id).get();
        clienteActualizado.setNombre(clienteDetails.getNombre());
        clienteActualizado.setCorreo(clienteDetails.getCorreo());
        clienteActualizado.setDireccion(clienteDetails.getDireccion());
        clienteActualizado.setTelefono(clienteDetails.getTelefono());
        return clienteRepo.save(clienteActualizado);
    }

    public void deleteCliente(int id) {
        Cliente cliente = clienteRepo.findById(id).get();
        clienteRepo.delete(cliente);
    }
}
