package com.newsahilhotel.service;

import com.newsahilhotel.dto.FoodCategoryRequestDto;
import com.newsahilhotel.dto.FoodCategoryResponseDto;
import com.newsahilhotel.dto.FoodCategoryUpdateRequestDto;

import java.util.List;

public interface FoodCategoryService {
    FoodCategoryResponseDto createCategory(FoodCategoryRequestDto request);

    FoodCategoryResponseDto getCategoryById(Long categoryId);

    List<FoodCategoryResponseDto> getAllCategories();

    List<FoodCategoryResponseDto> getActiveCategories();

    FoodCategoryResponseDto updateCategory(Long categoryId,
                                        FoodCategoryUpdateRequestDto request);

    void activateCategory(Long categoryId);

    void deactivateCategory(Long categoryId);

    void deleteCategory(Long categoryId);
}
