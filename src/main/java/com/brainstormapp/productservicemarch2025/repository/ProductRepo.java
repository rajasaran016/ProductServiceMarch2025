package com.brainstormapp.productservicemarch2025.repository;

import com.brainstormapp.productservicemarch2025.model.Category;
import com.brainstormapp.productservicemarch2025.model.Product;
import com.brainstormapp.productservicemarch2025.repository.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Optional<Product> findByIdAndDeletedIsFalse(Integer id);

    Optional<Product> findByTitle(String title);

    Optional<Product> findByDescription(String description);

    Optional<Product> findByDescriptionAndTitle(String description, String title);

    Optional<Product> findByCategoryId(Integer categoryId);

    Optional<List<Product>> findAllByCategory(Category category);

    void deleteById(Integer id);

    void deleteAllByCategory(Category category);


    Optional<List<Product>> findAllByDeletedIsFalse();

    @Query("select p.title from Product p where p.title =: title")
    ProductProjection findProductNameByTitle(@Param("tittle") String title);

    @Query("select p.title from Product p where p.category.title =: tittle")
    List<ProductProjection> findAllProductNameByTitle(@Param("title") String title);
}