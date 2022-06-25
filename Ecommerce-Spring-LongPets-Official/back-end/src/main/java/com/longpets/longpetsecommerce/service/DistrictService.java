package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.dto.response.FindAllWardByDistrictIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;

import java.util.List;

public interface DistrictService {
    public List<District> getAllDistricts();

    public List<FindAllWardByDistrictIdResponseDto> getAllWardOfDistrict(String districtId);
}
