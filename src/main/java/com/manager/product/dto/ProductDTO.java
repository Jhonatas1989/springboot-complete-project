package com.manager.product.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductDTO {

    private long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @Positive(message = "Price must be positive")
    @NotNull(message = "Price cannot be null")
    private Double price;

    public ProductDTO(long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
