package com.brainstormapp.productservicemarch2025.service;

import com.brainstormapp.productservicemarch2025.model.Product;
import java.util.List;

public interface ProductService {

    public Product getProductById(Integer id);
    public List<Product> getAllProducts();
    public Product createProduct(String title, String description, String image, String catTitle);


}
