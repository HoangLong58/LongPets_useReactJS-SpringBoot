package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.response.AllPetOfCategoryResponseDto;
import com.longpets.longpetsecommerce.dto.response.CategoryResponseDto;
import com.longpets.longpetsecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
