package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.response.AllWardByDistrictIdResponseDto;

import java.util.List;
import java.util.Optional;

public interface WardService {

    public List<AllWardByDistrictIdResponseDto> getAllWardByDistrictId(String districtId);

}
