package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;

import java.util.List;

public interface DistrictService {
    public List<District> getAllDistricts();
    public List<WardResponeDto> getAllWardOfDistrict(String districtId);
}
