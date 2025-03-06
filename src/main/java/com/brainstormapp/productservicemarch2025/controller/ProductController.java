package com.brainstormapp.productservicemarch2025.controller;

import com.brainstormapp.productservicemarch2025.DTO.createProductDTO;
import com.brainstormapp.productservicemarch2025.exceptions.ProductListIsEmptyException;
import com.brainstormapp.productservicemarch2025.exceptions.ProductNotFoundException;
import com.brainstormapp.productservicemarch2025.model.Product;
import com.brainstormapp.productservicemarch2025.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.*;
import com.brainstormapp.productservicemarch2025.service.FakeStoreProductService;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(@Qualifier("SelfProductService") ProductService inputService) {
        this.service = inputService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Integer id) throws ProductNotFoundException {

        //Null pointer exception handling
        if (id == 1000) {
            throw new IllegalArgumentException("id cannot be null");
        }

        Product product = service.getProductById(id); // service = new SelfProductService()
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        return product;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() throws ProductListIsEmptyException{

        List<Product> response = service.getAllProducts();
        if (response == null) {
            throw new ProductListIsEmptyException("Product list is empty");
        }

        return response;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody createProductDTO request) {

        return service.createProduct(request.getTitle(), request.getDescription(),
                request.getImage(), request.getCategory().getCatTitle());
    }

}
