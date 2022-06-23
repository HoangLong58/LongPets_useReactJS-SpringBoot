package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.data.repository.DistrictRepository;
import com.longpets.longpetsecommerce.data.repository.WardRepository;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;
import com.longpets.longpetsecommerce.service.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService {
    private WardRepository wardRepository;

    private DistrictRepository districtRepository;

    private ModelMapper modelMapper;

    @Autowired
    public WardServiceImpl(WardRepository wardRepository, DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.wardRepository = wardRepository;
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }

    public List<Ward> getAllWards() {
        return this.wardRepository.findAllWard();
    }
    @Override
    public List<WardResponeDto> getAllWardsDto() {
        List<Ward> wards = this.wardRepository.findAllWard();
        List<WardResponeDto> wardResponeDtos = new ArrayList<>();
        wards.forEach(ward -> {
            wardResponeDtos.add(modelMapper.map(ward, WardResponeDto.class));
        });
        return wardResponeDtos;
    }

    @Override
    public WardResponeDto getWardById(String id) {
        Optional<Ward> wardOptional = this.wardRepository.findById(id);
        Ward ward = wardOptional.get();
        return modelMapper.map(ward, WardResponeDto.class);
    }
}
