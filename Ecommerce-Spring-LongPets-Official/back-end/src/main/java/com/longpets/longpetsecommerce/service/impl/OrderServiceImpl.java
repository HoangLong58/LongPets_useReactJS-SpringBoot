package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.OrderRepository;
import com.longpets.longpetsecommerce.dto.request.AddOrderCartRequestDto;
import com.longpets.longpetsecommerce.dto.request.AddOrderRequestDto;
import com.longpets.longpetsecommerce.dto.response.AllDetailOrderByOrderIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import com.longpets.longpetsecommerce.dto.response.OrderByOrderDateResponseDto;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Override
    public void addOrder(AddOrderRequestDto addOrderRequestDto) {
//        Date order
        Date orderDate = new Date();
        try {
            orderRepository.addOrder(addOrderRequestDto.getCustomerId(),
                    addOrderRequestDto.getWardId(),
//                    addOrderRequestDto.getEmployeeId(),
                    addOrderRequestDto.getOrderName(),
                    addOrderRequestDto.getOrderEmail(),
                    addOrderRequestDto.getOrderPhone(),
                    addOrderRequestDto.getOrderAddress(),
                    addOrderRequestDto.getOrderNote(),
                    orderDate,
//                    addOrderRequestDto.getOrderStatusId(),
                    addOrderRequestDto.getOrderTotal());
        } catch (Exception exception) {
            throw new ApiRequestException("Error when add order");
        }
        List<OrderByOrderDateResponseDto> orders = orderRepository.getOrderByOrderDate(orderDate, addOrderRequestDto.getCustomerId(), addOrderRequestDto.getOrderPhone());
        if (orders.isEmpty()) {
            throw new ApiRequestException("Error: Can't find order");
        }
        OrderByOrderDateResponseDto order = orders.get(0);
        List<AddOrderCartRequestDto> cartItems =  addOrderRequestDto.getCart();
        cartItems.stream().forEach(cartItem -> {
            try {
                orderRepository.addOrderDetail(cartItem.getPetId(),
                        order.getOrderId(),
                        cartItem.getPetPriceDiscount(),
                        cartItem.getPetQuantityBuy(),
                        cartItem.getPetPriceDiscount() * cartItem.getPetQuantityBuy());
            } catch (Exception exception) {
                throw new ApiRequestException("Error: Can't add new order detail");
            }
            try {
                orderRepository.updatePetQuantityAfterOrder(cartItem.getPetQuantityBuy(), cartItem.getPetId());
            } catch (Exception exception) {
                throw new ApiRequestException("Error: Can't update new quantity of pet");
            }
        });
    }
}
