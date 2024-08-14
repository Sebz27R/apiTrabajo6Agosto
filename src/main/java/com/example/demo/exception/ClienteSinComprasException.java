package com.example.demo.exception;

public class ClienteSinComprasException extends RuntimeException {
    public ClienteSinComprasException(String message) {
        super(message);
    }
}