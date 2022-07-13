package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
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

    @PutMapping("/update-pet")
    void updatePet(@Valid @RequestBody UpdatePetRequestDto updatePetRequestDto) {
        petService.updatePet(updatePetRequestDto);
    }

    @GetMapping("/get-pet-by-pet-id/{petId}")
    List<PetAndCategoryAndImageByPetIdResponseDto> getPetCategoryImage(@PathVariable(value = "petId") Long petId) {
        return petService.getAllPetCategoryImageByPetId(petId);
    }

    @GetMapping("/get-all-pet-image/{petId}")
    List<AllPetImageResponseDto> getAllPetImage(@PathVariable(value = "petId") Long petId) {
        return petService.getAllPetImage(petId);
    }

    @PostMapping("/add-new-pet")
    void addNewPet(@Valid @RequestBody NewPetRequestDto newPetRequestDto) {
        petService.addNewPet(newPetRequestDto);
    }

    @DeleteMapping("/delete-pet/{petId}")
    void deletePet(@PathVariable(value = "petId") Long petId) {
        petService.deletePet(petId);
    }

    @GetMapping("/get-pet-category-image")
    List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImage() {
        return petService.getPetAndCategoryAndImage();
    }

    @GetMapping("/get-pet-category-image-by-pet-name/{petName}")
    List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImageByPetName(@PathVariable(value = "petName") String petName) {
        return petService.getPetAndCategoryAndImageByPetName(petName);
    }

    @GetMapping("/get-pet-quantity-count")
    PetQuantityCountResponseDto getPetQuantityCount() {
        return petService.getPetQuantityCount();
    }

//    ============================== FIX ================================
    @GetMapping("/get-all-pet")
    List<PetResponseDto> getAllPet() {
        return petService.findAllPet();
    }


}
