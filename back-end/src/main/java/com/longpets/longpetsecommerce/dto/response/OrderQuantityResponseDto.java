package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface OrderQuantityResponseDto {
    @Value("#{target.order_quantity}")
    Long getOrderQuantity();
}
