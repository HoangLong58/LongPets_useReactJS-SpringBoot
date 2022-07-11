package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface AllPetImageResponseDto {
    @Value("#{target.image_content}")
    String getImageContent();
}
