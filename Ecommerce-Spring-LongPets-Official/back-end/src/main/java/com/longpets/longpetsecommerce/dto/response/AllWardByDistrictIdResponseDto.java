package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface AllWardByDistrictIdResponseDto {
    @Value("#{target.ward_id}")
    String getWardId();

    @Value("#{target.ward_name}")
    String getWardName();

    @Value("#{target.ward_type}")
    String getWardType();

    @Value("#{target.district_id}")
    String getDistrictId();
}
