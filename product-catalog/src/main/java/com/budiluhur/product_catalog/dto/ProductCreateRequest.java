package com.budiluhur.product_catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCreateRequest {

    @NotBlank(message = "ID produk tidak boleh kosong")
    @Size(min = 3, max = 10, message = "ID produk harus antara 3 hingga 10 karakter")
    private String id;

    @NotBlank(message = "Nama produk tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Nama produk harus antara 3 hingga 100 karakter")
    private String name;

    @NotNull(message = "Harga produk tidak boleh null")
    @Min(value = 1000, message = "Harga produk minimal adalah Rp1.000")
    private Double price;

    public  ProductCreateRequest(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    

}
