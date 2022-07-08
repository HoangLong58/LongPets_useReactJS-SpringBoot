package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.request.AddOrderRequestDto;
import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import com.longpets.longpetsecommerce.dto.response.OrderByCustomerIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.OrderByOrderDateResponseDto;

import java.util.List;

public interface OrderService {
    List<AllOrderDetailOfOrderResponseDto> getAllOrderDetailOfOrder(Long orderId);

    List<AllPetOfOrderDetailResponseDto> getAllPetOfOrderDetail(Long orderId);

    void updateUserCancelOrder(Long orderId);

    void addOrder(AddOrderRequestDto addOrderRequestDto);

    List<OrderByCustomerIdResponseDto> getOrderByCustomerId(Long customerId);
}
