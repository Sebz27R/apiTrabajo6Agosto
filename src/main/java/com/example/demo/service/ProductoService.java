package com.example.demo.service;



import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepo productoRepo;

    public List<Producto> getAllProductos() {
        return productoRepo.findAll();
    }

    public Optional<Producto> getProductoById(int id) {
        return productoRepo.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepo.save(producto);
    }

    public Producto updateProducto(int id, Producto productoDetails) {
        Producto productoActualizado = productoRepo.findById(id).get();
        productoActualizado.setNombre(productoDetails.getNombre());
        productoActualizado.setDescripcion(productoDetails.getDescripcion());
        productoActualizado.setPrecio(productoDetails.getPrecio());
        productoActualizado.setStock(productoDetails.getStock());
        return productoRepo.save(productoActualizado);
    }

    public void deleteProducto(int id) {
        Producto producto = productoRepo.findById(id).get();
        productoRepo.delete(producto);
    }
}
