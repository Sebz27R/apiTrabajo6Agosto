package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cliente;

public interface ClienteRepo extends JpaRepository <Cliente, Integer>{
}
