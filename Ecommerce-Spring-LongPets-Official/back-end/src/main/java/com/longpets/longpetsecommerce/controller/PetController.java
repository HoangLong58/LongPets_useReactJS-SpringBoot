package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

//    ============================== FIX ================================
    @GetMapping("/get-all-pet")
    List<PetResponseDto> getAllPet() {
        return petService.findAllPet();
    }

    @GetMapping("/find-pet-by-name/{petName}")
    List<PetResponseDto> getAllPetSearchByName(@PathVariable(value = "petName") @NotNull String petName) {
        return petService.getAllPetSearchByName(petName);
    }

    @GetMapping("/{petId}")
    PetResponseDto getPetAndCategoryByPetId(@PathVariable(value = "petId") @NotNull @Min(0) Long petId) {
        return petService.getPetAndCategoryByPetId(petId);
    }

    @GetMapping("/get-all-pet-name-by-category-id/{categoryId}")
    List<PetNameResponseDto> getPetNameAndCategoryTitleByCategoryId(@PathVariable(value = "categoryId") @NotNull @Min(0) Long categoryId) {
        return petService.getAllPetName(categoryId);
    }

    @GetMapping("/get-pet-quantity-count")
    PetCountResponseDto getPetCount() {
        return petService.getPetCount();
    }

    @PostMapping("/add-new-pet")
    PetResponseDto addNewPet(@Valid @RequestBody NewPetRequestDto newPetRequestDto) {
        return petService.addPet(newPetRequestDto);
    }

    @PutMapping("/update-pet")
    PetResponseDto updatePet(@Valid @RequestBody UpdatePetRequestDto updatePetRequestDto) {
        return petService.updatePet(updatePetRequestDto);
    }

    @DeleteMapping("/delete-pet/{petId}")
    void deletePet(@PathVariable(value = "petId") @NotNull @Min(0) Long petId) {
        petService.deletePet(petId);
    }
}
