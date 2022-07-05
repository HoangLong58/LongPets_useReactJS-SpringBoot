package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.PetRepository;
import com.longpets.longpetsecommerce.dto.response.AllPetSearchByNameResponseDto;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    @Override
    public List<AllPetSearchByNameResponseDto> getAllPetSearchByName(String petName) {
        List<AllPetSearchByNameResponseDto> allPetSearchByNameResponseDtos = new ArrayList<>();
        try {
            allPetSearchByNameResponseDtos = petRepository.getAllPetSearchByName(petName);
        } catch(Exception e) {
//            throw new ApiRequestException("Loi tim kiem");
        throw e;
        }
        return allPetSearchByNameResponseDtos;
    }
}
