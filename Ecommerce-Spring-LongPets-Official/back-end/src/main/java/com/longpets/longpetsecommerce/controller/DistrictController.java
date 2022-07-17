package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.response.AllDistrictByCityIdResponseDto;
import com.longpets.longpetsecommerce.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping("/{cityId}")
    List<AllDistrictByCityIdResponseDto> getAllCity(@PathVariable(value = "cityId") String cityId) {
        return districtService.getAllDistrictByCityId(cityId);
    };
}
