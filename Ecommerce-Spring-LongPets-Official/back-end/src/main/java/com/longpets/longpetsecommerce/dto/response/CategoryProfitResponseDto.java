package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryProfitResponseDto {
    @Value("#{target.category_name}")
    String getCategoryName();

    @Value("#{target.money_total}")
    Long getMoneyTotal();
}
