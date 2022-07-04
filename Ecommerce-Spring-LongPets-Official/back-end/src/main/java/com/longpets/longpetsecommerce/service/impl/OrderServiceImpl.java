package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.OrderRepository;
import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import com.longpets.longpetsecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<AllOrderDetailOfOrderResponseDto> getAllOrderDetailOfOrder(Long orderId) {
        return orderRepository.getAllOrderDetailOfOrder(orderId);
    }

    @Override
    public List<AllPetOfOrderDetailResponseDto> getAllPetOfOrderDetail(Long orderId) {
        return orderRepository.getAllPetOfOrderDetail(orderId);
    }
}
