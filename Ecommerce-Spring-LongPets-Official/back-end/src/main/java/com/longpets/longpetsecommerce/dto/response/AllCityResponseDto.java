package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;


public interface AllCityResponseDto {

    @Value("#{target.city_id}")
    String getCityId();

    @Value("#{target.city_name}")
    String getCityName();

    @Value("#{target.city_type}")
    String getCityType();
}
