package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface MoneyTotalTodayResponseDto {
    @Value("#{target.money_total_today}")
    Long getMoneyTotalToday();
}
