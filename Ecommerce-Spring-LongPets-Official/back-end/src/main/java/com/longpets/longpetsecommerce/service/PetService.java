package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
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

    void addNewPet(NewPetRequestDto newPetRequestDto);

    void deletePet(Long petId);

    List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImage();

    List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImageByPetName(String petName);

    PetQuantityCountResponseDto getPetQuantityCount();

//    ===================================== FIX =================================
    List<PetResponseDto> findAllPet();



}
