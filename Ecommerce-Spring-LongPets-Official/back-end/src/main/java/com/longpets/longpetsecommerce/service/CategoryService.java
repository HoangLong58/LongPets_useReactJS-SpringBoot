package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.request.CategoryUpdateRequestDto;
import com.longpets.longpetsecommerce.dto.request.NewCategoryRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;

import java.util.List;

public interface CategoryService {

//    =================================== FIX ====================================
    List<CategoryResponseDto> getAllCategory();

    CategoryResponseDto getCategoryByCategoryId(Long categoryId);

    List<PetResponseDto> getPetOfCategoryByCategoryId(Long categoryId);

    List<CategoryResponseDto> getAllCategoryByCategoryName(String categoryName);

    CategoryResponseDto addCategory(NewCategoryRequestDto newCategoryRequestDto);

    CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto,
                                       Long categoryId);

    void deleteCategory(Long categoryId);

    CategoryCountDto getCategoryCount();



}
