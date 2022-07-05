package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.repository.CategoryRepository;
import com.longpets.longpetsecommerce.dto.response.AllPetOfCategoryResponseDto;
import com.longpets.longpetsecommerce.dto.response.CategoryResponseDto;
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
}
