package com.newsahilhotel.service;

import com.newsahilhotel.dto.FoodCategoryRequestDto;
import com.newsahilhotel.dto.FoodCategoryResponseDto;
import com.newsahilhotel.dto.FoodCategoryUpdateRequestDto;
import com.newsahilhotel.entity.FoodCategory;
import com.newsahilhotel.exception.BadRequestException;
import com.newsahilhotel.exception.ResourceNotFoundException;
import com.newsahilhotel.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService{
    final private FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public FoodCategoryResponseDto createCategory(FoodCategoryRequestDto request) {
        if(foodCategoryRepository.existsByName(request.getName())){
            throw new BadRequestException("FoodCategory already exist with name :"+request.getName());
        }
        FoodCategory foodCategory=mapToNormal(request);
        foodCategoryRepository.save(foodCategory);
        FoodCategoryResponseDto savedCategory=mapToResponse(foodCategory);
        return savedCategory;
    }

    @Override
    @Transactional
    public FoodCategoryResponseDto getCategoryById(Long categoryId) {
        FoodCategory foodCategory=foodCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id ;"+categoryId)
        );
        FoodCategoryResponseDto responseDto=mapToResponse(foodCategory);
        return responseDto;
    }

    @Override
    @Transactional
    public FoodCategoryResponseDto updateCategory(Long categoryId, FoodCategoryUpdateRequestDto request) {
        FoodCategory foodCategory=foodCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id ;"+categoryId)
        );
        foodCategory.setName(request.getName());
        foodCategory.setDescription(request.getDescription());
        foodCategoryRepository.save(foodCategory);
        FoodCategoryResponseDto responseDto=mapToResponse(foodCategory);
        return responseDto;
    }

    @Override
    @Transactional
    public List<FoodCategoryResponseDto> getActiveCategories() {
        List<FoodCategory> activeList=foodCategoryRepository.findByActiveTrue();
        List<FoodCategoryResponseDto> responseDtoList=new ArrayList<>();
        for(FoodCategory foodCategory: activeList){
            responseDtoList.add(mapToResponse(foodCategory));
        }
        return responseDtoList;
    }

    @Override
    @Transactional
    public List<FoodCategoryResponseDto> getAllCategories() {
        List<FoodCategory> allList=foodCategoryRepository.findAll();
        List<FoodCategoryResponseDto> responseDtoList=new ArrayList<>();
        for(FoodCategory foodCategory: allList){
            responseDtoList.add(mapToResponse(foodCategory));
        }
        return responseDtoList;
    }

    @Override
    @Transactional
    public void activateCategory(Long categoryId) {
        FoodCategory foodCategory=foodCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id ;"+categoryId)
        );
        foodCategory.setActive(true);
        foodCategoryRepository.save(foodCategory);
    }

    @Override
    @Transactional
    public void deactivateCategory(Long categoryId) {
        FoodCategory foodCategory=foodCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id ;"+categoryId)
        );
        foodCategory.setActive(false);
        foodCategoryRepository.save(foodCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        FoodCategory foodCategory=foodCategoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id ;"+categoryId)
        );
        foodCategoryRepository.delete(foodCategory);
    }

    private FoodCategoryResponseDto mapToResponse(FoodCategory category) {
        return FoodCategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .active(category.getActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
    private FoodCategory mapToNormal(FoodCategoryRequestDto category){
        return FoodCategory.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
