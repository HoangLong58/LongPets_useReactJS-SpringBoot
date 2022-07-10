package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.repository.CategoryRepository;
import com.longpets.longpetsecommerce.dto.request.CategoryUpdateRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        categories.stream().forEach(category -> {
            CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
            categoryResponseDtos.add(categoryResponseDto);
        });
        return categoryResponseDtos;
    }

    @Override
    public List<AllPetOfCategoryResponseDto> getAllPetOfCategory() {
        return categoryRepository.getAllPetOfCategory();
    }

    @Override
    public List<AllPetOfCategoryResponseDto> getAllPetOfCategoryByCategoryId(Long categoryId) {
        return categoryRepository.getAllPetOfCategoryByCategoryId(categoryId);
    }

    @Override
    public List<CategoryFindByCategoryNameResponseDto> getAllCategoryByCategoryName(String categoryName) {
        return categoryRepository.getAllCategoryByName(categoryName);
    }

    @Override
    public CategoryQuantityResponseDto getCategoryCategoryQuantity() {
        return categoryRepository.getCategoryQuantity();
    }

    @Override
    public Category updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = modelMapper.map(categoryUpdateRequestDto, Category.class);
        try {
            categoryRepository.updateCategory(category.getCategoryName(),
                    category.getCategoryTitle(),
                    category.getCategoryImage(),
                    category.getCategoryId());
            return category;
        } catch(Exception exception) {
            throw exception;
//            throw new ApiRequestException("Error: Can't update category");
        }
    }

    @Override
    public List<CategoryFindByCategoryIdResponseDto> findCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findCategoryByCategotyId(categoryId);
    }

    @Override
    public void addCategory(String categoryName, String categoryTitle, String categoryImage) {
        categoryRepository.addCategory(categoryName, categoryTitle, categoryImage);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteCategory(categoryId);
    }
}
