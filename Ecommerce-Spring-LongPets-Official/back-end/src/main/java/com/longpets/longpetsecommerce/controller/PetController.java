package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.response.AllPetSearchByNameResponseDto;
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
}
