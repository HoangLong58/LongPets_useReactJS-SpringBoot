package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.DistrictRepository;
import com.longpets.longpetsecommerce.dto.response.AllDistrictByCityIdResponseDto;
import com.longpets.longpetsecommerce.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    @Override
    public List<AllDistrictByCityIdResponseDto> getAllDistrictByCityId(String cityId) {
        return districtRepository.getAllDistrictByCityId(cityId);
    }
}
