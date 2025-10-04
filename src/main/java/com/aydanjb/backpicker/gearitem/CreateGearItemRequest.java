package com.aydanjb.backpicker.gearitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGearItemRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be 100 characters or fewer")
    private String name;

    @Min(value = 0, message = "Weight must be positive")
    private Double weight;

    @Min(value = 0, message = "Price must be positive")
    private Double price;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Size(max = 50, message = "Category must be 50 characters or fewer")
    private String category;

    public CreateGearItemRequest() {}

    public CreateGearItemRequest(String name, Double weight, Double price,
                                 Integer quantity, String category) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}