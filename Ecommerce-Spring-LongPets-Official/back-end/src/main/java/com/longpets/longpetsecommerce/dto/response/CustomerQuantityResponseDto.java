package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerQuantityResponseDto {
    @Value("#{target.customer_quantity}")
    Long getCustomerQuantity();
}
