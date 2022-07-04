package com.longpets.longpetsecommerce.dto.response;


import org.springframework.beans.factory.annotation.Value;

public interface PetImageResponseDto {
    @Value("#{target.image_id}")
    Long getImageId();

    @Value("#{target.image_content}")
    String getImageContent();
}
