package com.newsahilhotel.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodCategoryUpdateRequestDto {
    @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters.")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    private String description;
}
