package com.example.spring_mvc.dto;

import jakarta.validation.constraints.*;

public class ProductDTO {

    @NotNull(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Mô tả không được để trống")
    @Max(value = 255, message = "Mô tả không được quá 255 ký tự")
    private String description;

    @NotNull(message = "Giá không được để trống")
    @PositiveOrZero(message = "Giá không được âm")
    private Double price;

    @NotNull(message = "Thể loại không được để trống")
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