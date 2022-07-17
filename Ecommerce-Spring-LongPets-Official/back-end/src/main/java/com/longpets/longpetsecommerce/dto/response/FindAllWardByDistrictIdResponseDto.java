package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface FindAllWardByDistrictIdResponseDto {
    String getDistrictId();

    String getDistrictName();

    @Value("#{target.wardId}")
    String getWardId();

    @Value("#{target.wardName}")
    String getWardName();

    @Value("#{target.wardType}")
    String getWardType();
}
