package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.OrderRepository;
import com.longpets.longpetsecommerce.dto.response.AllDetailOrderByOrderIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
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

    @Override
    public void updateUserCancelOrder(Long orderId) {
        try {
            orderRepository.updateUserCancelOrder(orderId);
        } catch (Exception exception) {
            throw new ApiRequestException("Error when update user cancel order");
        }
//        orderRepository.updateUserCancelOrder(orderId);
        List<AllDetailOrderByOrderIdResponseDto> allDetailOrderByOrderId = orderRepository.findAllDetailOrderByOrderId(orderId);
        if (allDetailOrderByOrderId.isEmpty()) {
            throw new ApiRequestException("No order detail of this order");
        }
        allDetailOrderByOrderId.stream().forEach(orderDetail -> {
            orderRepository.updatePetQuantityInOrderDetail(orderDetail.getOrderDetailQuantity(), orderDetail.getPetId());
        });
    }
}
