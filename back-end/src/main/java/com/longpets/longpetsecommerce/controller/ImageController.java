package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.response.PetImageResponseDto;
import com.longpets.longpetsecommerce.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{petId}")
    public List<PetImageResponseDto> getPetImageByPetId(@PathVariable Long petId) {
        return imageService.getPetImage(petId);
    }
}
