package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface OrderTodayResponseDto {
    @Value("#{target.order_quantity_today}")
    Long getOrderQuantityToday();
}
