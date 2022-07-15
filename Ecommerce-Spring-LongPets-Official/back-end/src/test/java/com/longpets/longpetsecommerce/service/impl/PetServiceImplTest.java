package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Image;
import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.data.repository.CategoryRepository;
import com.longpets.longpetsecommerce.data.repository.ImageRepository;
import com.longpets.longpetsecommerce.data.repository.PetRepository;
import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
import com.longpets.longpetsecommerce.dto.response.PetNameResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetResponseDto;
import com.longpets.longpetsecommerce.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PetServiceImplTest {

//    private Category category;
//    private Optional<Category> categoryOptional;
//    private Pet pet;
//    private List<Pet> pets;
//    private List<String> petNames;
//    private PetNameResponseDto petNameResponseDto;
//    private List<PetNameResponseDto> petNameResponseDtos;
//    private PetServiceImpl petServiceImpl;
//    private CategoryRepository categoryRepository;
//    private PetRepository petRepository;
//
//    @BeforeEach
//    void setting() {
//        categoryRepository = mock(CategoryRepository.class);
//        petRepository = mock(PetRepository.class);
//
//        category = mock(Category.class);
//        categoryOptional = Optional.of(category);
//
//        pet = mock(Pet.class);
//        pets = List.of(pet);
//
//        petNames = mock(List.class);
//
//        petNameResponseDto = mock(PetNameResponseDto.class);
//        petNameResponseDtos = List.of(petNameResponseDto);
//
//        petServiceImpl = mock(PetServiceImpl.class);
//    }

    @Test
    public void addPet_WhenNewPetRequestDtoValid_Expect_ReturnPetResponseDto() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        PetRepository petRepository = mock(PetRepository.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        ImageRepository imageRepository = mock(ImageRepository.class);
        PetServiceImpl petService = mock(PetServiceImpl.class);

        Image image = mock(Image.class);
        List<Image> images = List.of(image);
        Pet pet = mock(Pet.class);
        Category category = mock(Category.class);
        Optional<Category> categoryOptional = Optional.of(category);
        NewPetRequestDto newPetRequestDto = mock(NewPetRequestDto.class);

        PetResponseDto petResponseDto = mock(PetResponseDto.class);

        PetResponseDto actualPetResponseDto = petService.addPet(newPetRequestDto);

        when(categoryRepository.findCategoryByCategoryId(newPetRequestDto.getCategoryId()))
                .thenReturn(categoryOptional);
        when(modelMapper.map(newPetRequestDto, Pet.class))
                .thenReturn(pet);

        when(petRepository.saveAndFlush(pet))
                .thenReturn(pet);
        when(categoryRepository.findCategoryByCategoryId(newPetRequestDto.getCategoryId()))
                .thenReturn(categoryOptional);

        when(modelMapper.map(pet, PetResponseDto.class)).thenReturn(petResponseDto);

        assertThat(petResponseDto).isEqualTo(actualPetResponseDto);
    }

    @Test
    public void getPetAndCategoryByPetId_WhenPetIdInvalid_Expect_ReturnResourceNotFoundException() {
        PetRepository petRepository = mock(PetRepository.class);

        when(petRepository.findPetByPetId(1L)).thenThrow(new ResourceNotFoundException("Can't find pet id 1"));
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> petRepository.findPetByPetId(1L));

        assertThat(exception.getMessage()).isEqualTo("Can't find pet id 1");
    }

    @Test
    public void getPetAndCategoryByPetId_WhenPetIdValid_Expect_ReturnPetResponseDto() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        PetRepository petRepository = mock(PetRepository.class);
        PetResponseDto petResponseDtoExpect = mock(PetResponseDto.class);
        Pet pet = mock(Pet.class);
        PetServiceImpl petService = mock(PetServiceImpl.class);

        when(petRepository.findPetByPetId(1L)).thenReturn(Optional.of(pet));
        when(modelMapper.map(pet, PetResponseDto.class)).thenReturn(petResponseDtoExpect);
        assertThat(petResponseDtoExpect).isEqualTo(petService.getPetAndCategoryByPetId(1L));
    }
}
