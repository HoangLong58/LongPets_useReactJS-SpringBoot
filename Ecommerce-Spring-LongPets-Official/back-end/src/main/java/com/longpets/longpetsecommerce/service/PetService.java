package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.response.AllPetSearchByNameResponseDto;

import java.util.List;

public interface PetService {
    List<AllPetSearchByNameResponseDto> getAllPetSearchByName(String petName);
}
