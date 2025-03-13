package com.brainstormapp.productservicemarch2025.service;

import com.brainstormapp.productservicemarch2025.exceptions.ProductListIsEmptyException;
import com.brainstormapp.productservicemarch2025.exceptions.ProductNotFoundException;
import com.brainstormapp.productservicemarch2025.model.Category;
import com.brainstormapp.productservicemarch2025.model.Product;
import com.brainstormapp.productservicemarch2025.repository.CategoryRepo;
import com.brainstormapp.productservicemarch2025.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Integer id) throws ProductNotFoundException {
        Optional<Product> response = productRepo.findByIdAndDeletedIsFalse(id);
        if (response.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        return response.get();
    }

    @Override
    public List<Product> getAllProducts() throws ProductListIsEmptyException {
        Optional <List<Product>> response = productRepo.findAllByDeletedIsFalse();
        if (response.isEmpty()) {
            throw new ProductListIsEmptyException("Product not found");
        }
        return response.get();
    }

    @Override
    public Product createProduct(String title, String description, String image, String catTitle) {

        validateInputRequest(title, image, catTitle, description);

        Product product = new Product();
        Category category = new Category();

        product.setTitle(title);
        product.setDescription(description);
        product.setImage(image);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        Optional<Category> existingCategory = categoryRepo.findByTitle(catTitle);
        if (existingCategory.isEmpty()) {
            category.setTitle(catTitle);
            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());
        }else{
            category = existingCategory.get();
        }
        // saved category also.
        product.setCategory(category);

        // Finally save to the DB.
        Product response = productRepo.save(product);
        return response;
    }

    private void validateInputRequest(String title, String image, String catTitle, String description) {
        if (title == null ) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }
        if (catTitle == null ) {
            throw new IllegalArgumentException("CatTitle cannot be null");
        }
        if (description == null ) {
            throw new IllegalArgumentException("Description cannot be null");
        }
    }


}
