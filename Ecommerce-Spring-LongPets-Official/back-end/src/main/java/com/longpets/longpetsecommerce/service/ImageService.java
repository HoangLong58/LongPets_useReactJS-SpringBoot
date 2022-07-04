package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.response.AllPetOfCategoryResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetImageResponseDto;

import java.util.List;

public interface ImageService {
    List<PetImageResponseDto> getPetImage(Long petId);

}
