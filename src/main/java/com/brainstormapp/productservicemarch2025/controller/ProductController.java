package com.brainstormapp.productservicemarch2025.controller;

import com.brainstormapp.productservicemarch2025.DTO.createProductDTO;
import com.brainstormapp.productservicemarch2025.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brainstormapp.productservicemarch2025.service.FakeStoreProductService;

import java.util.List;

@RestController
public class ProductController {

    private final FakeStoreProductService service;

    public ProductController(FakeStoreProductService inputService) {
        this.service = inputService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Integer id) {

        //Null pointer exception handling
        if (id == 1000) {
            throw new IllegalArgumentException("id cannot be null");
        }

        return service.getProductById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return service.getAllProducts();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody createProductDTO request) {

        return service.createProduct(request.getTitle(), request.getDescription(),
                request.getImage(), request.getCategory().getCatTitle());
    }

//    @GetMapping("/hello")
//    public ResponseEntity<String> hello() {
//        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
//    }
}
