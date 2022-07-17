package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;

import java.util.List;

public interface PetService {

//    ===================================== FIX =================================
    List<PetResponseDto> findAllPet();

    List<PetResponseDto> getAllPetSearchByName(String petName);

    PetResponseDto getPetAndCategoryByPetId(Long petId);

    List<PetNameResponseDto> getAllPetName(Long categoryId);

    PetCountResponseDto getPetCount();

    PetResponseDto addPet(NewPetRequestDto newPetRequestDto);

    PetResponseDto updatePet(UpdatePetRequestDto updatePetRequestDto);
    void deletePet(Long categoryId);
}
