package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.dto.response.AllDistrictByCityIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllWardByDistrictIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;

import java.util.List;
import java.util.Optional;

public interface WardService {
//    public List<WardResponeDto> getAllWardsDto();
////
////    List<Ward> getAllWards();
////
////    public WardResponeDto getWardById(String id);

    public List<AllWardByDistrictIdResponseDto> getAllWardByDistrictId(String districtId);

}
