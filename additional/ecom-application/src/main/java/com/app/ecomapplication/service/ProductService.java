package com.app.ecomapplication.service;

import com.app.ecomapplication.entity.Product;
import com.app.ecomapplication.entity.dto.ProductRequest;
import com.app.ecomapplication.entity.dto.ProductResponse;
import com.app.ecomapplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> GetAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public ProductResponse GetProduct(Long id) {
        return mapToProductResponse(productRepository.findById(id).orElseThrow());
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        mapToProduct(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow();
        mapToProduct(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    public Boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setActive(false);
                    productRepository.save(p);
                    return true;
                })
                .orElse(false);
    }

    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchProduct(keyword)
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    private void mapToProduct(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setActive(true);
        product.setImageUrl(productRequest.getImageUrl());
        product.setDescription(productRequest.getDescription());
    }

    private ProductResponse mapToProductResponse(Product product) {
        var response = new ProductResponse();
        response.setId(String.valueOf(product.getId()));
        response.setActive(product.getActive());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategory(product.getCategory());
        response.setImageUrl(product.getImageUrl());
        return response;
    }
}
