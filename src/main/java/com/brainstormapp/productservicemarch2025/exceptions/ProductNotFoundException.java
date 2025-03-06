package com.brainstormapp.productservicemarch2025.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}