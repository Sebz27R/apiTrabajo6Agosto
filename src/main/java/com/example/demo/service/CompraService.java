package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Compra;
import com.example.demo.entity.Producto;
import com.example.demo.exception.*;
import com.example.demo.repository.ClienteRepo;
import com.example.demo.repository.CompraRepo;
import com.example.demo.repository.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ProductoRepo productoRepo;

    public Compra crearCompra(Compra compra) {
        if (compra.getCantidad() <= 0) {
            throw new CantidadNegativaException("La cantidad debe ser mayor que cero.");
        }
        // Validar existencia de Cliente
        Optional<Cliente> clienteOpt = clienteRepo.findById(compra.getIdCliente());
        if (!clienteOpt.isPresent()) {
            throw new ClienteNotFoundException("Cliente no encontrado con ID: " + compra.getIdCliente());
        }

        // Validar existencia de Producto
        Optional<Producto> productoOpt = productoRepo.findById(compra.getIdProducto());
        if (!productoOpt.isPresent()) {
            throw new ProductoNotFoundException("Producto no encontrado con ID: " + compra.getIdProducto());
        }

        Producto producto = productoOpt.get();

        // Verificar si hay suficiente stock
        if (producto.getStock() < compra.getCantidad()) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        // Reducir el stock del producto
        producto.setStock(producto.getStock() - compra.getCantidad());

        // Guardar la compra y actualizar el producto
        productoRepo.save(producto);
        return compraRepo.save(compra);
    }

    public List<Compra> obtenerComprasPorCliente(int idCliente) {
        // Validar existencia de Cliente
        Optional<Cliente> clienteOpt = clienteRepo.findById(idCliente);
        if (!clienteOpt.isPresent()) {
            throw new ClienteNotFoundException("Cliente no encontrado con ID: " + idCliente);
        }

        // Obtener compras del cliente
        List<Compra> compras = compraRepo.findByIdCliente(idCliente);
        if (compras.isEmpty()) {
            throw new ClienteSinComprasException("El cliente con ID: " + idCliente + " no tiene compras.");
        }

        return compras;
    }
}

