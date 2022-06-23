package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;
import com.longpets.longpetsecommerce.service.DistrictService;
import com.longpets.longpetsecommerce.service.WardService;
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
    List<WardResponeDto> getAllWardOfDistrict(@RequestBody String districtId) {
        return this.districtService.getAllWardOfDistrict(districtId);
    }

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }
}
