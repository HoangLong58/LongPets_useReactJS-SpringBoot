package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.response.AllWardByDistrictIdResponseDto;
import com.longpets.longpetsecommerce.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/ward")
public class WardController {

    private final WardService wardService;

    @GetMapping("/{districtId}")
    List<AllWardByDistrictIdResponseDto> getAllCity(@PathVariable(value = "districtId") String districtId) {
        return wardService.getAllWardByDistrictId(districtId);
    };
}
