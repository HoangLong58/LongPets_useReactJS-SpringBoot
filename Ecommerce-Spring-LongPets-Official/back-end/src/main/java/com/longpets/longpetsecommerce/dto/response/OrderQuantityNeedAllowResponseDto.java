package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface OrderQuantityNeedAllowResponseDto {
    @Value("#{target.order_quantity_need_allow}")
    Long getOrderQuantityNeedAllow();
}
