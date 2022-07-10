package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface MoneyTotalResponseDto {
    @Value("#{target.money_total}")
    Long getMoneyTotal();
}
