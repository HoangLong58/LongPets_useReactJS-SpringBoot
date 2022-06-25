package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;
import com.longpets.longpetsecommerce.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ward")
public class WardController {
    private WardService wardService;

    @GetMapping
    List<Ward> getAllWards() {
         return this.wardService.getAllWards();
    }

    @GetMapping(path = "/{wardId}")
    WardResponeDto getWardById(@PathVariable("wardId") String wardId) {
        return this.wardService.getWardById(wardId);
    }

    @Autowired
    public WardController(WardService wardService) {
        this.wardService = wardService;
    }
}
