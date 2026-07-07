package com.newsahilhotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodCategoryRequestDto {
    @NotBlank(message = "Category name is required.")
    @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters.")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    private String description;
}
