package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.request.CategoryUpdateRequestDto;
import com.longpets.longpetsecommerce.dto.request.NewCategoryRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    List<CategoryResponseDto> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{categoryId}")
    List<AllPetOfCategoryResponseDto> getAllPetOfCategoryByCategoryId(@PathVariable Long categoryId) {
        return categoryService.getAllPetOfCategoryByCategoryId(categoryId);
    }

    @GetMapping("/get-all-pet")
    List<AllPetOfCategoryResponseDto> getAllPetOfCategory() {
        return categoryService.getAllPetOfCategory();
    }

    @GetMapping("/find-category/{categoryName}")
    List<CategoryFindByCategoryNameResponseDto> getCategoryFindByCategoryName(@PathVariable(value = "categoryName") String categoryName) {
        return categoryService.getAllCategoryByCategoryName(categoryName);
    }

    @GetMapping("/get-category-quantity")
    CategoryQuantityResponseDto getCategoryQuantity() {
        return categoryService.getCategoryCategoryQuantity();
    }

    @PutMapping("/update-category")
    ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto) {
       return ResponseEntity.ok().body(categoryService.updateCategory(categoryUpdateRequestDto));
    }

    @GetMapping("/find-category-by-category-id/{categoryId}")
    List<CategoryFindByCategoryIdResponseDto> findCategoryByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryService.findCategoryByCategoryId(categoryId);
    }

    @PostMapping("/add-category")
    void addCategory(@Valid @RequestBody NewCategoryRequestDto newCategoryRequestDto) {
       categoryService.addCategory(newCategoryRequestDto.getCategoryName(), newCategoryRequestDto.getCategoryTitle(), newCategoryRequestDto.getCategoryImage());
    }

    @DeleteMapping("/delete-category/{categoryId}")
    void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
