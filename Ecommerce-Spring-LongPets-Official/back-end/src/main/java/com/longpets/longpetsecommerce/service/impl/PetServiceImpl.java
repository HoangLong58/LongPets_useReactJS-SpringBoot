package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.PetRepository;
import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
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

    @Override
    public List<PetAndCategoryByPetIdResponseDto> getPetAndCategoryByPetId(Long petId) {
        return petRepository.getPetAndCategoryByPetId(petId);
    }

    @Override
    public List<PetNameAndCategoryTitleByCategoryIdResponseDto> getPetNameAndCategoryTitleByCategoryId(Long categoryId) {
        return petRepository.getPetNameAndCategoryTitleByCategoryId(categoryId);
    }

    @Override
    public void updatePet(UpdatePetRequestDto updatePetRequestDto) {
        try {
            petRepository.updatePet(updatePetRequestDto.getCategoryId(),
                    updatePetRequestDto.getPetName(),
                    updatePetRequestDto.getPetGender(),
                    updatePetRequestDto.getPetAge(),
                    updatePetRequestDto.getPetVaccinated(),
                    updatePetRequestDto.getPetHealthWarranty(),
                    updatePetRequestDto.getPetTitle(),
                    updatePetRequestDto.getPetDescription(),
                    updatePetRequestDto.getPetNote(),
                    updatePetRequestDto.getPetQuantity(),
                    updatePetRequestDto.getPetPrice(),
                    updatePetRequestDto.getPetPriceDiscount(),
                    updatePetRequestDto.getPetId());
            try {
                petRepository.deletePetImage(updatePetRequestDto.getPetId());
                try {
                    List<String> petImage = updatePetRequestDto.getPetImage();
                    petImage.stream().forEach(image -> {
                        petRepository.addPetImage(updatePetRequestDto.getPetId(), image);
                    });
                }catch (Exception exception) {
                    throw new ApiRequestException("Error when add new pet image");
                }
            } catch (Exception exception) {
                throw new ApiRequestException("Error when delete old pet image");
            }
        } catch (Exception exception) {
            throw new ApiRequestException("Error when update pet");
        }
    }

    @Override
    public List<PetAndCategoryAndImageByPetIdResponseDto> getAllPetCategoryImageByPetId(Long petId) {
        return petRepository.getPetAndCategoryAndImageByPetId(petId);
    }

    @Override
    public List<AllPetImageResponseDto> getAllPetImage(Long petId) {
        return petRepository.getAllPetImage(petId);
    }
}
