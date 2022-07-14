package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.data.repository.DistrictRepository;
import com.longpets.longpetsecommerce.data.repository.WardRepository;
import com.longpets.longpetsecommerce.dto.response.AllWardByDistrictIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;
import com.longpets.longpetsecommerce.service.WardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WardServiceImpl implements WardService {
    private final WardRepository wardRepository;

    @Override
    public List<AllWardByDistrictIdResponseDto> getAllWardByDistrictId(String districtId) {
        return wardRepository.getAllWardByDistrictId(districtId);
    }
}
