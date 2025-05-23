package com.example.spring_mvc.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

@Value
public class ProductDTO implements Serializable {

    @NotNull(message = "Product name cannot be empty")
     String name;

    @NotNull(message = "Description cannot be empty")
     String description;

    @NotNull(message = "Price cannot be empty")
    @PositiveOrZero(message = "Price cannot be negative")
     Double price;

    @NotNull(message = "Category cannot be empty")
     String category;

    boolean deleted = false;

    public ProductDTO(String name, String description, Double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
    public boolean isDeleted() {
        return deleted;
    }
}