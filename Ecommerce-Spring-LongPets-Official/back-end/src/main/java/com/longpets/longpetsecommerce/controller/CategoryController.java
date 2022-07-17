package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.request.CategoryUpdateRequestDto;
import com.longpets.longpetsecommerce.dto.request.NewCategoryRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


//    ===================== FIX ======================
    @GetMapping
    List<CategoryResponseDto> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/get-category/{categoryId}")
    CategoryResponseDto getCategoryByCategoryId(@PathVariable @Min(0) Long categoryId) {
        return categoryService.getCategoryByCategoryId(categoryId);
    }

    @GetMapping("/{categoryId}")
    List<PetResponseDto> getPetOfCategoryByCategoryId(@PathVariable @Min(0) Long categoryId) {
        return categoryService.getPetOfCategoryByCategoryId(categoryId);
    }

    @GetMapping("/find-category/{categoryName}")
    List<CategoryResponseDto> getCategoryByCategoryName(@PathVariable(value = "categoryName") String categoryName) {
        return categoryService.getAllCategoryByCategoryName(categoryName);
    }

    @PostMapping("/add-category")
    CategoryResponseDto addCategory(@Valid @RequestBody NewCategoryRequestDto newCategoryRequestDto) {
        return categoryService.addCategory(newCategoryRequestDto);
    }

    @PutMapping("/update-category/{categoryId}")
    CategoryResponseDto updateCategory(@Valid @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto,
                                       @PathVariable(value = "categoryId") @Min(0) Long categoryId) {
        return categoryService.updateCategory(categoryUpdateRequestDto, categoryId);
    }

    @DeleteMapping("/delete-category/{categoryId}")
    void deleteCategory(@PathVariable @Min(0) Long categoryId) {
         categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/get-category-quantity")
    CategoryCountDto getCategoryQuantity() {
        return categoryService.getCategoryCount();
    }
}
