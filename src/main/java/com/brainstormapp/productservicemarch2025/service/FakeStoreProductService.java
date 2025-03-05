package com.brainstormapp.productservicemarch2025.service;

import com.brainstormapp.productservicemarch2025.DTO.FakeStoreProductDTO;
import com.brainstormapp.productservicemarch2025.model.Category;
import com.brainstormapp.productservicemarch2025.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(Integer id) {
        Product product = new Product();

        // step 1 : Call the FakeStore API and store the response body in FakeStoreProductDTO
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponse
                = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponse.getBody();

        // step 2 : convert the FakeStoreProductDTO response body to product

        if (fakeStoreProductDTO == null) {
            throw new IllegalArgumentException("Product not found");
        }

        product = convertFakeStoreDTOResponseToProduct(fakeStoreProductDTO);

        // step 3 : return the product response
        return product;
    }


    private Product convertFakeStoreDTOResponseToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        Category category = new Category();

        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setId(fakeStoreProductDTO.getId());
        category.setTitle(fakeStoreProductDTO.getTitle());
        product.setCategory(category);

        return product;
    }

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        // Step 1 : Call Fakestore Api and get the response
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreProductDTOResponse =
                restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        if (fakeStoreProductDTOResponse.getBody() == null) {
            throw new NullPointerException("Product not found");
        }

        // step 2 : converting all FakestoreProduct response to Product
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOResponse.getBody()) {
            Product product = new Product();

            product = convertFakeStoreDTOResponseToProduct(fakeStoreProductDTO);

            products.add(product);
        }

        return products;
    }


    public Product createProduct() {
        return null;
    }

    public Product createProduct(String title, String description, String image, String catTitle) {

        Product response = new Product();

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setCategory(catTitle);

        // post the received data to the FakeStoreAPI
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponse
                = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDTO, FakeStoreProductDTO.class);

        if (fakeStoreProductDTOResponse.getBody() == null) {
            throw new IllegalArgumentException("Product not found");
        }

        // convert the FakestoreDTO response to product response
        response = convertFakeStoreDTOResponseToProduct(fakeStoreProductDTOResponse.getBody());

        //return product
        return response;

    }
}
