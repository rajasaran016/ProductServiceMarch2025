package com.brainstormapp.productservicemarch2025.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel {
    private String title;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> products;
}
