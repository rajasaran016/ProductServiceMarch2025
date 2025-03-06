package com.brainstormapp.productservicemarch2025.service;

import com.brainstormapp.productservicemarch2025.exceptions.ProductListIsEmptyException;
import com.brainstormapp.productservicemarch2025.exceptions.ProductNotFoundException;
import com.brainstormapp.productservicemarch2025.model.Product;
import java.util.List;

public interface ProductService {

    public Product getProductById(Integer id) throws ProductNotFoundException;
    public List<Product> getAllProducts() throws ProductListIsEmptyException;
    public Product createProduct(String title, String description, String image, String catTitle);


}
