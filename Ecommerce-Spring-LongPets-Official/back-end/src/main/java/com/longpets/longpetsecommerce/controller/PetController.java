package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.response.AllPetSearchByNameResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetAndCategoryByPetIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetNameAndCategoryTitleByCategoryIdResponseDto;
import com.longpets.longpetsecommerce.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @GetMapping("/find-pet-by-name/{petName}")
    List<AllPetSearchByNameResponseDto> getAllPetSearchByName(@PathVariable(value = "petName") String petName) {
        return petService.getAllPetSearchByName(petName);
    }

    @GetMapping("/{petId}")
    List<PetAndCategoryByPetIdResponseDto> getPetAndCategoryByPetId(@PathVariable(value = "petId") Long petId) {
        return petService.getPetAndCategoryByPetId(petId);
    }

    @GetMapping("/get-all-pet-name-by-category-id/{categoryId}")
    List<PetNameAndCategoryTitleByCategoryIdResponseDto> getPetNameAndCategoryTitleByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        return petService.getPetNameAndCategoryTitleByCategoryId(categoryId);
    }
}
