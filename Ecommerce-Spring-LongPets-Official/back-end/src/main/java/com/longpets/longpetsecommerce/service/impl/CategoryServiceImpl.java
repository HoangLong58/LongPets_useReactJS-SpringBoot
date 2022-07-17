package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.data.repository.CategoryRepository;
import com.longpets.longpetsecommerce.dto.request.CategoryUpdateRequestDto;
import com.longpets.longpetsecommerce.dto.request.NewCategoryRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.exception.ResourceNotFoundException;
import com.longpets.longpetsecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

//    =========================== FIX ===========================
    @Override
    public List<CategoryResponseDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = modelMapper.map(categories,
                new TypeToken<List<CategoryResponseDto>>() {
                }.getType());

        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto getCategoryByCategoryId(Long categoryId) {
         Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find category with id "+ categoryId));
         CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
         return categoryResponseDto;
    }

    @Override
    public List<PetResponseDto> getPetOfCategoryByCategoryId(Long categoryId) {
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find category with id "+ categoryId));
        List<Pet> pets = category.getPets();
        List<PetResponseDto> petResponseDtos = modelMapper.map(pets,
                new TypeToken<List<PetResponseDto>>() {
                }.getType());
        return petResponseDtos;
    }

    @Override
    public List<CategoryResponseDto> getAllCategoryByCategoryName(String categoryName) {
        List<Category> categories = categoryRepository.findCategoryByCategoryNameContaining(categoryName);
        List<CategoryResponseDto> categoryResponseDtos = modelMapper.map(categories,
                new TypeToken<List<CategoryResponseDto>>() {
                }.getType());

        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto addCategory(NewCategoryRequestDto newCategoryRequestDto) {
        Category category = modelMapper.map(newCategoryRequestDto, Category.class);
        categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
        return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto,
                                              Long categoryId) {
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find category by category " + categoryId));
        modelMapper.map(categoryUpdateRequestDto, category);
        category = categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
        return categoryResponseDto;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryCountDto getCategoryCount() {
        Long categoryCount = categoryRepository.count();
        CategoryCountDto categoryCountDto = new CategoryCountDto();
        categoryCountDto.setCategoryQuantity(categoryCount);
        return categoryCountDto;
    }
}
