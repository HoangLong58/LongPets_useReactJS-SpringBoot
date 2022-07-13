package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.request.AcceptOrderRequestDto;
import com.longpets.longpetsecommerce.dto.request.AddOrderRequestDto;
import com.longpets.longpetsecommerce.dto.request.DenyOrderRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
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
    void addOrder(@Valid @RequestBody AddOrderRequestDto addOrderRequestDto) {
        orderService.addOrder(addOrderRequestDto);
    }

    @GetMapping("/get-order-by-customer-id/{customerId}")
    List<OrderByCustomerIdResponseDto> getOrderByCustomerId(@PathVariable(value = "customerId") Long customerId) {
        return orderService.getOrderByCustomerId(customerId);
    }

    @GetMapping("/get-dog-profit-year")
    CategoryProfitResponseDto getDogProfitYear() {
        return orderService.getDogProfitYear();
    }

    @GetMapping("/get-dog-profit/{day}-{month}-{year}")
    CategoryProfitResponseDto getDogProfit(@PathVariable(value = "day") Long day, @PathVariable(value = "month") Long month, @PathVariable(value = "year") Long year) {
        return orderService.getDogProfit(day, month, year);
    }

    @GetMapping("/get-cat-profit-year")
    CategoryProfitResponseDto getCatProfitYear() {
        return orderService.getCatProfitYear();
    }

    @GetMapping("/get-cat-profit/{day}-{month}-{year}")
    CategoryProfitResponseDto getCatProfit(@PathVariable(value = "day") Long day, @PathVariable(value = "month") Long month, @PathVariable(value = "year") Long year) {
        return orderService.getCatProfit(day, month, year);
    }

    @GetMapping("/get-another-profit-year")
    CategoryProfitResponseDto getAnotherProfitYear() {
        return orderService.getAnotherProfitYear();
    }

    @GetMapping("/get-another-profit/{day}-{month}-{year}")
    CategoryProfitResponseDto getAnotherProfit(@PathVariable(value = "day") Long day, @PathVariable(value = "month") Long month, @PathVariable(value = "year") Long year) {
        return orderService.getAnotherProfit(day, month, year);
    }

    @GetMapping("/get-money-total-year")
    MoneyTotalResponseDto getMoneyTotalYear() {
        return orderService.getMoneyTotalYear();
    }

    @GetMapping("/get-money-total/{day}-{month}-{year}")
    MoneyTotalResponseDto getMoneyTotal(@PathVariable(value = "day") Long day, @PathVariable(value = "month") Long month, @PathVariable(value = "year") Long year) {
        return orderService.getMoneyTotal(day, month, year);
    }

    @GetMapping("/get-money-total-12-month")
    List<MoneyTotal12MonthResponseDto> getMoneyTotal12Month() {
        return orderService.getMoneyTotal12Month();
    }

    @GetMapping("/get-order-today")
    OrderTodayResponseDto getOrderToday() {
        return orderService.getOrderToday();
    }

    @GetMapping("/get-money-total-today")
    MoneyTotalTodayResponseDto getMoneyTotalToday() {
        return orderService.getMoneyTotalToday();
    }

    @GetMapping("/get-order-need-allow")
    OrderQuantityNeedAllowResponseDto getOrderQuantityNeedAllow() {
        return orderService.getOrderQuantityNeedAllow();
    }

    @GetMapping("/get-all-order")
    List<AllOrderResponseDto> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/get-all-order-by-order-id/{orderId}")
    List<AllOrderResponseDto> getAllOrderByOrderId(@PathVariable(value = "orderId") Long orderId) {
        return orderService.getAllOrderByOrderId(orderId);
    }

    @GetMapping("/get-order-quantity")
    OrderQuantityResponseDto getOrderQuantity() {
        return orderService.getOrderQuantity();
    }

    @PutMapping("/accept-order")
    void acceptOrder(@Valid @RequestBody AcceptOrderRequestDto acceptOrderRequestDto) {
        try {
            orderService.acceptOrder(acceptOrderRequestDto.getOrderId(), acceptOrderRequestDto.getEmployeeId(), acceptOrderRequestDto.getEmployeeName(), acceptOrderRequestDto.getEmployeeAvatar());
        } catch (Exception exception) {
            throw new ApiRequestException("Error when accept order");
        }
    }

    @PutMapping("/deny-order")
    void denyOrder(@Valid @RequestBody DenyOrderRequestDto denyOrderRequestDto) {
        try {
            orderService.denyOrder(denyOrderRequestDto.getOrderId(), denyOrderRequestDto.getEmployeeId(), denyOrderRequestDto.getEmployeeName(), denyOrderRequestDto.getEmployeeAvatar());
        } catch (Exception exception) {
            throw new ApiRequestException("Error when deny order");
        }
    }





}
