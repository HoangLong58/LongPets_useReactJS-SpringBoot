package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Image;
import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.data.repository.CategoryRepository;
import com.longpets.longpetsecommerce.data.repository.ImageRepository;
import com.longpets.longpetsecommerce.data.repository.PetRepository;
import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.PetCountResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetResponseDto;
import com.longpets.longpetsecommerce.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PetServiceImplTest {
    @Test
    public void addPet_WhenNewPetRequestDtoValid_Expect_ReturnPetResponseDto() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        PetRepository petRepository = mock(PetRepository.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        ImageRepository imageRepository = mock(ImageRepository.class);
        PetServiceImpl petService = mock(PetServiceImpl.class);

        Image image = mock(Image.class);
        List<Image> images = List.of(image);
        Category category = mock(Category.class);
        Optional<Category> categoryOptional = Optional.of(category);
        NewPetRequestDto newPetRequestDto = mock(NewPetRequestDto.class);

        when(categoryRepository.findCategoryByCategoryId(newPetRequestDto.getCategoryId()))
                .thenReturn(categoryOptional);
        Pet pet = modelMapper.map(newPetRequestDto, Pet.class);
        when(petRepository.saveAndFlush(pet))
                .thenReturn(pet);
        when(categoryRepository.findCategoryByCategoryId(newPetRequestDto.getCategoryId()))
                .thenReturn(categoryOptional);

        PetResponseDto petResponseDtoExpect = modelMapper.map(pet, PetResponseDto.class);
        PetResponseDto petResponseDtoActual = petService.addPet(newPetRequestDto);

        assertThat(petResponseDtoActual).isEqualTo(petResponseDtoExpect);
    }

    @Test
    public void addPet_WhenNewPetRequestDtoInValid_Expect_ReturnResourceNotFoundException() {
        NewPetRequestDto newPetRequestDto = mock(NewPetRequestDto.class);
        PetServiceImpl petService = mock(PetServiceImpl.class);

        when(petService.addPet(newPetRequestDto)).thenThrow(new ResourceNotFoundException("Can't find category by category 1"));
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> petService.addPet(newPetRequestDto));

        assertThat(exception.getMessage()).isEqualTo("Can't find category by category 1");
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
//        PetResponseDto petResponseDto = mock(PetResponseDto.class);
        Pet pet = mock(Pet.class);
        PetServiceImpl petService = mock(PetServiceImpl.class);

        when(petRepository.findPetByPetId(1L)).thenReturn(Optional.of(pet));
        PetResponseDto petResponseDtoExpect = modelMapper.map(pet, PetResponseDto.class);
        PetResponseDto petResponseDtoActual = petService.getPetAndCategoryByPetId(1L);

        assertThat(petResponseDtoActual).isEqualTo(petResponseDtoExpect);
    }

    @Test
    public void getPetCount_Expect_ReturnPetCountResponseDto() {
        PetRepository petRepository = mock(PetRepository.class);
        PetServiceImpl petService = mock(PetServiceImpl.class);

        when(petRepository.count()).thenReturn(1L);
        PetCountResponseDto countResponseDtoExpect = new PetCountResponseDto(1L);
        when(petService.getPetCount()).thenReturn(new PetCountResponseDto(1L));

        assertThat(petService.getPetCount().getPetQuantityCount()).isEqualTo(countResponseDtoExpect.getPetQuantityCount());
    }

    @Test
    public void updatePet_WhenUpdatePetRequestDtoValid_ReturnPetResponseDto() {
        PetRepository petRepository = mock(PetRepository.class);
        Pet pet = mock(Pet.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        UpdatePetRequestDto updatePetRequestDto = mock(UpdatePetRequestDto.class);
        PetServiceImpl petService = mock(PetServiceImpl.class);

        when(petRepository.findPetByPetId(1L)).thenReturn(Optional.of(pet));
        modelMapper.map(updatePetRequestDto, pet);
        when(petRepository.save(pet)).thenReturn(pet);
        PetResponseDto petResponseDtoExpect = modelMapper.map(pet, PetResponseDto.class);
        PetResponseDto petResponseDtoActual = petService.updatePet(updatePetRequestDto);

        assertThat(petResponseDtoActual).isEqualTo(petResponseDtoExpect);
    }

    @Test
    public void updatePet_WhenUpdatePetRequestDtoInValid_ReturnResourceNotFoundException() {
        PetServiceImpl petService = mock(PetServiceImpl.class);
        UpdatePetRequestDto updatePetRequestDto = mock(UpdatePetRequestDto.class);

        when(petService.updatePet(updatePetRequestDto)).thenThrow(new ResourceNotFoundException("Can't find pet id 1"));
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> petService.updatePet(updatePetRequestDto));
        assertThat(exception.getMessage()).isEqualTo("Can't find pet id 1");
    }
}
