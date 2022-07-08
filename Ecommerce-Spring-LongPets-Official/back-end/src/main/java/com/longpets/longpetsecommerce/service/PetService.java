package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.response.AllPetSearchByNameResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetAndCategoryByPetIdResponseDto;

import com.longpets.longpetsecommerce.dto.response.PetNameAndCategoryTitleByCategoryIdResponseDto;

import java.util.List;

public interface PetService {
    List<AllPetSearchByNameResponseDto> getAllPetSearchByName(String petName);

    List<PetAndCategoryByPetIdResponseDto> getPetAndCategoryByPetId(Long petId);

    List<PetNameAndCategoryTitleByCategoryIdResponseDto> getPetNameAndCategoryTitleByCategoryId(Long categoryId);
}
