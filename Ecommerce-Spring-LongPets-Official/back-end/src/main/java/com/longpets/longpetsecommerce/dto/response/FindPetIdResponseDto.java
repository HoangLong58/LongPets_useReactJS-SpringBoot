package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface FindPetIdResponseDto {
    @Value("#{target.pet_id}")
    Long getPetId();
}
