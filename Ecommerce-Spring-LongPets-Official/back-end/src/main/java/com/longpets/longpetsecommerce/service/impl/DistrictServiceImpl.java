package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.data.repository.DistrictRepository;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;
import com.longpets.longpetsecommerce.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private DistrictRepository districtRepository;

    private ModelMapper modelMapper;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<District> getAllDistricts() {
        return this.districtRepository.findAllDistrict();
    };

    @Override
    public List<WardResponeDto> getAllWardOfDistrict(String districtId) {
         List<Ward> wards = this.districtRepository.findAllWardByDistrictId(districtId);
         List<WardResponeDto> wardResponeDtos = new ArrayList<>();
         wards.forEach(ward -> {
             wardResponeDtos.add(modelMapper.map(ward, WardResponeDto.class));
         });
         return wardResponeDtos;
    }
}
