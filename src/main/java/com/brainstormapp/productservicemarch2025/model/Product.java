package com.brainstormapp.productservicemarch2025.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseModel {

    private String title;
    private String description;
    private String image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Category category;
}
