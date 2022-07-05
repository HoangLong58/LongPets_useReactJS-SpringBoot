package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.CityRepository;
import com.longpets.longpetsecommerce.dto.response.AllCityResponseDto;
import com.longpets.longpetsecommerce.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    @Override
    public List<AllCityResponseDto> getAllCity() {
        return cityRepository.getAllCity();
    }
}
