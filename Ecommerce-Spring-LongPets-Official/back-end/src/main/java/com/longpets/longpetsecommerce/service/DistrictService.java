package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.response.AllDistrictByCityIdResponseDto;

import java.util.List;

public interface DistrictService {

    public List<AllDistrictByCityIdResponseDto> getAllDistrictByCityId(String cityId);
}
