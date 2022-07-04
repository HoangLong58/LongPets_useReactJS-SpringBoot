package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.AdminLog;
import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.dto.response.AllPetOfCategoryResponseDto;
import com.longpets.longpetsecommerce.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    public List<CategoryResponseDto> getAllCategory();

    List<AllPetOfCategoryResponseDto> getAllPetOfCategory(Long categoryId);
}
