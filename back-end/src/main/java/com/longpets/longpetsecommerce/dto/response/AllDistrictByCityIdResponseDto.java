package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface AllDistrictByCityIdResponseDto {
    @Value("#{target.district_id}")
    String getDistrictId();

    @Value("#{target.district_name}")
    String getDistrictName();

    @Value("#{target.district_type}")
    String getDistrictType();

    @Value("#{target.city_id}")
    String getCityId();
}
