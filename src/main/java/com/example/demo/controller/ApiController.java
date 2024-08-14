package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;
import com.example.demo.entity.Compra;
import com.example.demo.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CompraService compraService;

    @GetMapping(value = "/")
    public String getPage(){
        return "Hello World";
    }

    @GetMapping(value = "/clientes")
    public List<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @PostMapping(value = "/clientes/guardar")
    public ResponseEntity<String> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
            );
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        clienteService.saveCliente(cliente);
        return new ResponseEntity<>("Cliente creado con éxito", HttpStatus.OK);
    }

    @PutMapping(value = "/clientes/actualizar/{id}")
    public String updateCliente(@PathVariable int id, @RequestBody Cliente cliente){
        clienteService.updateCliente(id, cliente);
        return "Cliente actualizado..";
    }

    @DeleteMapping(value = "/clientes/borrar/{id}")
    public String deleteCliente(@PathVariable int id){
        clienteService.deleteCliente(id);
        return "Se borro el usuario con id: " + id;
    }


    @GetMapping(value = "/productos")
    public List<Producto> getProductos(){
        return productoService.getAllProductos();
    }

    @PostMapping(value = "/productos/guardar")
    public ResponseEntity<String> crearProducto(@Valid @RequestBody Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
            );
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        productoService.saveProducto(producto);
        return new ResponseEntity<>("Producto creado con éxito", HttpStatus.OK);
    }

    @PutMapping(value = "/productos/actualizar/{id}")
    public String updateProducto(@PathVariable int id, @RequestBody Producto producto){
        productoService.updateProducto(id, producto);
        return "Producto actualizado..";
    }

    @DeleteMapping(value = "/productos/borrar/{id}")
    public String deleteProducto(@PathVariable int id){
        productoService.deleteProducto(id);
        return "Se borro el producto con id: " + id;
    }

    @PostMapping(value = "/compras/crear")
    public Compra createCompra(@RequestBody Compra compra) {
        return compraService.crearCompra(compra);
    }

    @GetMapping("/compras/cliente/{idCliente}")
    public List<Compra> getComprasPorCliente(@PathVariable int idCliente) {
        return compraService.obtenerComprasPorCliente(idCliente);
    }


}

