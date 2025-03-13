package com.brainstormapp.productservicemarch2025.repository;

import com.brainstormapp.productservicemarch2025.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Optional<Category> findById(Integer id);

    Optional<Category> findByTitle(String catTitle);

}
