package com.aydanjb.backpicker.gearlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGearListRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be 100 characters or fewer")
    private String name;

    @Size(max = 500, message = "Description must be 500 characters or fewer")
    private String description;

    public CreateGearListRequest() {
    }

    public CreateGearListRequest(String name, String description) {
        this.name = name;
        this.description = description;
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
}