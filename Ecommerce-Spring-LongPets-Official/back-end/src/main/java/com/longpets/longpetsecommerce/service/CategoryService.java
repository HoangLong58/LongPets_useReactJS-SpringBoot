package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.AdminLog;
import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.dto.request.CategoryUpdateRequestDto;
import com.longpets.longpetsecommerce.dto.request.NewCategoryRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;

import java.util.List;

public interface CategoryService {
//    public List<CategoryResponseDto> getAllCategory();

//    List<AllPetOfCategoryResponseDto> getAllPetOfCategoryByCategoryId(Long categoryId);

//    List<AllPetOfCategoryResponseDto> getAllPetOfCategory();

//    List<CategoryFindByCategoryNameResponseDto> getAllCategoryByCategoryName(String categoryName);

//    CategoryQuantityResponseDto getCategoryCategoryQuantity();

//    Category updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);

//    List<CategoryFindByCategoryIdResponseDto> findCategoryByCategoryId(Long categoryId);

//    void addCategory(String categoryName, String categoryTitle, String categoryImage);

//    void deleteCategory(Long categoryId);

//    =================================== FIX ====================================
    List<CategoryResponseDto> getAllCategory();

    CategoryResponseDto getCategoryByCategoryId(Long categoryId);

    List<PetResponseDto> getPetOfCategoryByCategoryId(Long categoryId);

    List<CategoryResponseDto> getAllCategoryByCategoryName(String categoryName);

    CategoryResponseDto addCategory(NewCategoryRequestDto newCategoryRequestDto);

    CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto,
                                       Long categoryId);

    void deleteCategory(Long categoryId);

    CategoryQuantityResponseDto getCategoryCategoryQuantity();




}
