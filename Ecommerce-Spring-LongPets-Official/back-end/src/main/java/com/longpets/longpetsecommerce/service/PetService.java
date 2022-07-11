package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;

import java.util.List;

public interface PetService {
    List<AllPetSearchByNameResponseDto> getAllPetSearchByName(String petName);

    List<PetAndCategoryByPetIdResponseDto> getPetAndCategoryByPetId(Long petId);

    List<PetNameAndCategoryTitleByCategoryIdResponseDto> getPetNameAndCategoryTitleByCategoryId(Long categoryId);

    void updatePet(UpdatePetRequestDto updatePetRequestDto);

    List<PetAndCategoryAndImageByPetIdResponseDto> getAllPetCategoryImageByPetId(Long petId);

    List<AllPetImageResponseDto> getAllPetImage(Long petId);
}
