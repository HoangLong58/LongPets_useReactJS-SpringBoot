package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryQuantityResponseDto {
    @Value("#{target.category_quantity}")
    Long getCategoryQuantity();
}
