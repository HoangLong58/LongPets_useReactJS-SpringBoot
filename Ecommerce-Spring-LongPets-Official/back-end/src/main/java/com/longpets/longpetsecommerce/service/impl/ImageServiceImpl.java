package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.ImageRepository;
import com.longpets.longpetsecommerce.dto.response.PetImageResponseDto;
import com.longpets.longpetsecommerce.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    @Override
    public List<PetImageResponseDto> getPetImage(Long petId) {
        return imageRepository.getPetImage(petId);
    }
}
