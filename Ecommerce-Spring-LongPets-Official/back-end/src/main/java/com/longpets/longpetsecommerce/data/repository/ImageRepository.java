package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Image;
import com.longpets.longpetsecommerce.dto.response.PetImageResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select i.image_id ,i.image_content from pet p join image i on p.pet_id = i.pet_id where p.pet_id = ?",
            nativeQuery = true)
    List<PetImageResponseDto> getPetImage(Long petId);
}
