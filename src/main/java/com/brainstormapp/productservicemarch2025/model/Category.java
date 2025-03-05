package com.brainstormapp.productservicemarch2025.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends BaseModel {
    private String title;
}
