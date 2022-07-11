package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface WardDistrictCityResponseDto {
    @Value("#{target.ward_id}")
    String getWardId();

    @Value("#{target.ward_name}")
    String getWardName();

    @Value("#{target.ward_type}")
    String getWardType();

    @Value("#{target.district_id}")
    String getDistrictId();

    @Value("#{target.district_name}")
    String getDistrictName();

    @Value("#{target.district_type}")
    String getDistrictType();

    @Value("#{target.city_id}")
    String getCityId();

    @Value("#{target.city_name}")
    String getCityName();

    @Value("#{target.city_type}")
    String getCityType();
}
