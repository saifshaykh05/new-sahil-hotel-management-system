package com.newsahilhotel.controller;

import com.newsahilhotel.dto.FoodCategoryRequestDto;
import com.newsahilhotel.dto.FoodCategoryResponseDto;
import com.newsahilhotel.dto.FoodCategoryUpdateRequestDto;
import com.newsahilhotel.dto.StdApiResponse;
import com.newsahilhotel.service.FoodCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.BeanProperty;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v-1/food-categories")
@RequiredArgsConstructor
public class FoodCategoryController {
    private final FoodCategoryService foodCategoryService;
    @PostMapping
    public ResponseEntity<StdApiResponse<FoodCategoryResponseDto>> createCategory (@Valid @RequestBody FoodCategoryRequestDto requestDto){
        FoodCategoryResponseDto responseDto=foodCategoryService.createCategory(requestDto);
        StdApiResponse<FoodCategoryResponseDto> apiResponse=new StdApiResponse<>(true,"Food Category created Successfully",responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @GetMapping
    public ResponseEntity<StdApiResponse<List<FoodCategoryResponseDto>>> getAllFoodCategory(){
        List<FoodCategoryResponseDto> responseDtoList=foodCategoryService.getAllCategories();
        StdApiResponse<List<FoodCategoryResponseDto>> apiResponse= StdApiResponse.<List<FoodCategoryResponseDto>>builder()
                .status(true)
                .message("All Food Categories")
                .data(responseDtoList).build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StdApiResponse<FoodCategoryResponseDto>> getCategoryById(@PathVariable Long id){
        FoodCategoryResponseDto responseDto=foodCategoryService.getCategoryById(id);
        StdApiResponse<FoodCategoryResponseDto> apiResponse= StdApiResponse.<FoodCategoryResponseDto>builder()
                .status(true)
                .message("Successfully found Categeory by Id:"+id)
                .data(responseDto).build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/active")
    public ResponseEntity<StdApiResponse<List<FoodCategoryResponseDto>>> getActiveCategory (){
        List<FoodCategoryResponseDto> responseDtoList=foodCategoryService.getActiveCategories();
        StdApiResponse<List<FoodCategoryResponseDto>> apiResponse= StdApiResponse.<List<FoodCategoryResponseDto>>builder()
                .status(true)
                .message("All Active Food Categories")
                .data(responseDtoList).build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StdApiResponse<FoodCategoryResponseDto>> updateFoodCategory(@PathVariable Long id,@Valid @RequestBody FoodCategoryUpdateRequestDto requestDto){
        FoodCategoryResponseDto responseDto=foodCategoryService.updateCategory(id,requestDto);
        StdApiResponse<FoodCategoryResponseDto> apiResponse= StdApiResponse.<FoodCategoryResponseDto>builder()
                .status(true)
                .message("Successfully upadated Food Category of Id:"+ id)
                .data(responseDto).build();
        return ResponseEntity.ok(apiResponse);
    }
    @PatchMapping("/{id}/activate")
    public ResponseEntity<StdApiResponse<Void>> activateFoodCategory(@PathVariable Long id){
        foodCategoryService.activateCategory(id);
        StdApiResponse<Void> apiResponse= StdApiResponse.<Void>builder()
                .status(true)
                .message("Food category activated successfully.")
                .data(null).build();
        return ResponseEntity.ok(apiResponse);
    }
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<StdApiResponse<Void>> deactivateCategory(
            @PathVariable Long id) {

        foodCategoryService.deactivateCategory(id);

        StdApiResponse<Void> apiResponse =
                StdApiResponse.<Void>builder()
                        .status(true)
                        .message("Food category deactivated successfully.")
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StdApiResponse<Void>> deleteCategory(
            @PathVariable Long id) {

        foodCategoryService.deleteCategory(id);

        StdApiResponse<Void> apiResponse =
                StdApiResponse.<Void>builder()
                        .status(true)
                        .message("Food category deleted successfully.")
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

}
