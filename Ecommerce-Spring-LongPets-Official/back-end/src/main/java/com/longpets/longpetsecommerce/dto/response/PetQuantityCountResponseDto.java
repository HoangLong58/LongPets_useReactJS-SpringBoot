package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface PetQuantityCountResponseDto {
    @Value("#{target.pet_quantity_count}")
    Long getPetQuantityCount();
}
