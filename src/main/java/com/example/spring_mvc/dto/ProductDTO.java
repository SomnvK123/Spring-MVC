package com.example.spring_mvc.dto;

import jakarta.validation.constraints.*;

public class ProductDTO {

    @NotNull(message = "Product name cannot be empty")
    private String name;

    @NotNull(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be empty")
    @PositiveOrZero(message = "Price cannot be negative")
    private Double price;

    @NotNull(message = "Category cannot be empty")
    private String category;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, Double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}