package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.dto.response.FindAllWardByDistrictIdResponseDto;
import com.longpets.longpetsecommerce.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {
    private DistrictService districtService;

    @GetMapping
    List<District> getAllDistricts() {
        return this.districtService.getAllDistricts();
    }

    @PostMapping
    List<FindAllWardByDistrictIdResponseDto> getAllWardOfDistrict() {
        return this.districtService.getAllWardOfDistrict("250");
    }

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }
}
