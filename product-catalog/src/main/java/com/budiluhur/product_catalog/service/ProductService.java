package com.budiluhur.product_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) throws ProductNotFoundException {
        return productRepository.findById(id)
         .orElseThrow(() ->
                new ProductNotFoundException(
                    "Produk dengan ID '" + id
                    + "' tidak ditemukan!"));

    }

    public Product createProduct(Product product) {
         // Simpan ke database via JPA
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct)
            throws ProductNotFoundException {
       Product existingProduct = getProductById(id);
       existingProduct.setName(updatedProduct.getName());
       existingProduct.setPrice(updatedProduct.getPrice());
       return  productRepository.save(existingProduct);
    }

    public void  deleteProduct(String id) throws ProductNotFoundException {
       Product existingProduct = getProductById(id);
       productRepository.delete(existingProduct);
    }

}
