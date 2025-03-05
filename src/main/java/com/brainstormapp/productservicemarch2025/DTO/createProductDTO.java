package com.brainstormapp.productservicemarch2025.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createProductDTO {
    private String title;
    private String description;
    private String image;
    private CategoryDTO category;
}
