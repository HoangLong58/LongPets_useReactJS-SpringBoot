package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.request.AddOrderRequestDto;
import com.longpets.longpetsecommerce.dto.response.AllOrderDetailOfOrderResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfCategoryResponseDto;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import com.longpets.longpetsecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/get-all-order-detail/{orderId}")
    List<AllOrderDetailOfOrderResponseDto> getAllOrderDetailOfOrder(@PathVariable Long orderId) {
        return orderService.getAllOrderDetailOfOrder(orderId);
    }

    @GetMapping("/get-all-pet/{orderId}")
    List<AllPetOfOrderDetailResponseDto> getAllPetOfOrderDetail(@PathVariable Long orderId) {
        return orderService.getAllPetOfOrderDetail(orderId);
    };

    @PutMapping("/user-cancel-order/{orderId}")
    void updateUserCancelOrder(@PathVariable(value = "orderId") Long orderId) {
        orderService.updateUserCancelOrder(orderId);
    }

    @PostMapping("/add-order")
    void addOrder(@RequestBody AddOrderRequestDto addOrderRequestDto) {
        orderService.addOrder(addOrderRequestDto);
    }
}
