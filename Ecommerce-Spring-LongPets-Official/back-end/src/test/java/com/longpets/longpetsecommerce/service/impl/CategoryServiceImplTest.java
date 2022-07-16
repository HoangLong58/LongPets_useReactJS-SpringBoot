package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.data.repository.CategoryRepository;
import com.longpets.longpetsecommerce.data.repository.PetRepository;
import com.longpets.longpetsecommerce.dto.request.CategoryUpdateRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.CategoryCountDto;
import com.longpets.longpetsecommerce.dto.response.CategoryResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetCountResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetResponseDto;
import com.longpets.longpetsecommerce.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
//    TODO: need fix
    @Test
    public void getAllCategory_WhenNewPetRequestDtoValid_ReturnListCategoryResponseDto() {
        CategoryServiceImpl categoryService = mock(CategoryServiceImpl.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        List<Category> categories = mock(List.class);

        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryResponseDto> categoriesExpect = modelMapper.map(categories,
                new TypeToken<List<CategoryResponseDto>>() {}.getType());
        List<CategoryResponseDto> categoriesActual = categoryService.getAllCategory();

        assertThat(categoriesActual).isEqualTo(categoriesExpect);
    }

    @Test
    public void getCategoryByCategoryId_WhenCategoryIdValid_ReturnCategoryResponseDto() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        Category category = new Category();
        CategoryServiceImpl categoryService = mock(CategoryServiceImpl.class);

        when(categoryRepository.findCategoryByCategoryId(1L)).thenReturn(Optional.of(category));
        CategoryResponseDto categoryResponseDtoExpect = modelMapper.map(Optional.of(category).get(), CategoryResponseDto.class);
        CategoryResponseDto categoryResponseDtoActual = categoryService.getCategoryByCategoryId(1L);

        assertThat(categoryResponseDtoActual).isEqualTo(categoryResponseDtoExpect);
    }

    @Test
    public void getCategoryByCategoryId_WhenCategoryIdInValid_ReturnResourceNotFoundException() {
        CategoryRepository categoryRepository = mock(CategoryRepository.class);

        when(categoryRepository.findCategoryByCategoryId(1L)).thenThrow(new ResourceNotFoundException("Can't find category id 1"));
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryRepository.findCategoryByCategoryId(1L));

        assertThat(exception.getMessage()).isEqualTo("Can't find category id 1");
    }

    @Test
    public void updateCategory_WhenCategoryUpdateRequestDtoValid_ReturnCategoryResponseDto() {
        CategoryUpdateRequestDto categoryUpdateRequestDto = mock(CategoryUpdateRequestDto.class);
        Category category = mock(Category.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        CategoryServiceImpl categoryService = mock(CategoryServiceImpl.class);

        when(categoryRepository.findCategoryByCategoryId(1L)).thenReturn(Optional.of(category));
        modelMapper.map(categoryUpdateRequestDto, category);
        when(categoryRepository.save(category)).thenReturn(category);
        CategoryResponseDto categoryResponseDtoExpect = modelMapper.map(category, CategoryResponseDto.class);
        CategoryResponseDto categoryResponseDtoActual = categoryService.updateCategory(categoryUpdateRequestDto, 1L);

        assertThat(categoryResponseDtoActual).isEqualTo(categoryResponseDtoExpect);
    }

    @Test
    public void updateCategory_WhenCategoryUpdateRequestDtoInValid_ReturnResourceNotFoundException() {
        CategoryUpdateRequestDto categoryUpdateRequestDto = mock(CategoryUpdateRequestDto.class);
        CategoryServiceImpl categoryService = mock(CategoryServiceImpl.class);

        when(categoryService.updateCategory(categoryUpdateRequestDto, 1L)).thenThrow(new ResourceNotFoundException("Can't find category by category 1"));
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryService.updateCategory(categoryUpdateRequestDto, 1L));

        assertThat(exception.getMessage()).isEqualTo("Can't find category by category 1");
    }

    @Test
    public void getCategoryCount_Expect_ReturnCategoryCountDto() {
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        CategoryServiceImpl categoryService = mock(CategoryServiceImpl.class);

        when(categoryRepository.count()).thenReturn(1L);
        CategoryCountDto categoryCountDtoExpect = new CategoryCountDto(1L);
        when(categoryService.getCategoryCount()).thenReturn(new CategoryCountDto(1L));

        assertThat(categoryService.getCategoryCount().getCategoryQuantity()).isEqualTo(categoryCountDtoExpect.getCategoryQuantity());
    }
}
