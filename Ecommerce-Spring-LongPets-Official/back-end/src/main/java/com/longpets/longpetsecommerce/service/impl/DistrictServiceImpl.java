package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.data.repository.DistrictRepository;
import com.longpets.longpetsecommerce.dto.response.AllDistrictByCityIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.FindAllWardByDistrictIdResponseDto;
import com.longpets.longpetsecommerce.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DistrictServiceImpl implements DistrictService {

//    private DistrictRepository districtRepository;
//
//    private ModelMapper modelMapper;
//
//    @Autowired
//    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper) {
//        this.districtRepository = districtRepository;
//        this.modelMapper = modelMapper;
//    }
//
////    Get all district
//    @Override
//    public List<District> getAllDistricts() {
//        return this.districtRepository.findAllDistrict();
//    };
//
////    Get all ward of district
//    @Override
//    public List<FindAllWardByDistrictIdResponseDto> getAllWardOfDistrict(String districtId) {
//        return this.districtRepository.findAllWardByDistrictId(districtId);
//    }


    private final DistrictRepository districtRepository;
    @Override
    public List<AllDistrictByCityIdResponseDto> getAllDistrictByCityId(String cityId) {
        return districtRepository.getAllDistrictByCityId(cityId);
    }
}
