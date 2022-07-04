package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;

import java.util.List;

public interface OrderService {
    List<AllOrderDetailOfOrderResponseDto> getAllOrderDetailOfOrder(Long orderId);
    List<AllPetOfOrderDetailResponseDto> getAllPetOfOrderDetail(Long orderId);
}
