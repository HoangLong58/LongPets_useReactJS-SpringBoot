package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/order-detail")
public class OrderDetailController {

}
