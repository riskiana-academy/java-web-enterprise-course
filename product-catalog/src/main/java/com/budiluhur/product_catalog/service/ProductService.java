package com.budiluhur.product_catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.budiluhur.product_catalog.dto.ProductCreateRequest;
import com.budiluhur.product_catalog.dto.ProductResponse;
import com.budiluhur.product_catalog.exception.ProductNotFoundException;
import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    // constructor injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = new ArrayList<>();
        for (Product p : products) {
            responses.add(mapToResponse(p));
        }
        return responses;

    }

    public ProductResponse getProductById(String id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Produk dengan ID '" + id
                                + "' tidak ditemukan!"));
        return mapToResponse(product);

    }

    public ProductResponse createProduct(ProductCreateRequest request) {
        Product product = new Product(
                request.getId(),
                request.getName(),
                request.getPrice());
        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);

    }

    public Product updateProduct(String id, Product updatedProduct)
            throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Produk dengan ID '" + id + "' tidak ditemukan!"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(String id) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Produk dengan ID '" + id + "' tidak ditemukan!"));
        productRepository.delete(existingProduct);
    }

    // Helper: konversi Entity ke DTO
    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice());
    }

}
