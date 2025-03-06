package com.brainstormapp.productservicemarch2025.service;

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
    public Product getProductById(Integer id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String description, String image, String catTitle) {

        validateInputRequest(title, image, catTitle, description);

        Product product = new Product();
        Category category;

        product.setTitle(title);
        product.setDescription(description);
        product.setImage(image);
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        Optional<Category> optionalCategory = categoryRepo.findByTitle(catTitle);

        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        } else {
            category = new Category();
            category.setTitle(catTitle);
            // Save the new category if needed
            category = categoryRepo.save(category);
        }

        product.setCategory(category);

        // save the product in to database
        return productRepo.save(product);
    }

    private void validateInputRequest(String title, String image, String catTitle, String description) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }


}
