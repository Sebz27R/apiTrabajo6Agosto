package com.example.demo.exception;

public class CantidadNegativaException extends RuntimeException {
    public CantidadNegativaException(String message) {
        super(message);
    }
}
