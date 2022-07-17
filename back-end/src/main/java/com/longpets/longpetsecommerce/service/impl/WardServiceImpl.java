package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.WardRepository;
import com.longpets.longpetsecommerce.dto.response.AllWardByDistrictIdResponseDto;
import com.longpets.longpetsecommerce.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
