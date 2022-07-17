package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.request.AddOrderRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;

import java.util.List;

public interface OrderService {
    List<AllOrderDetailOfOrderResponseDto> getAllOrderDetailOfOrder(Long orderId);

    List<AllPetOfOrderDetailResponseDto> getAllPetOfOrderDetail(Long orderId);

    void updateUserCancelOrder(Long orderId);

    void addOrder(AddOrderRequestDto addOrderRequestDto);

    List<OrderByCustomerIdResponseDto> getOrderByCustomerId(Long customerId);

    CategoryProfitResponseDto getDogProfit(Long day, Long month, Long year);
    CategoryProfitResponseDto getDogProfitYear();

    CategoryProfitResponseDto getCatProfit(Long day, Long month, Long year);
    CategoryProfitResponseDto getCatProfitYear();

    CategoryProfitResponseDto getAnotherProfit(Long day, Long month, Long year);
    CategoryProfitResponseDto getAnotherProfitYear();

    MoneyTotalResponseDto getMoneyTotal(Long day, Long month, Long year);
    MoneyTotalResponseDto getMoneyTotalYear();
    List<MoneyTotal12MonthResponseDto> getMoneyTotal12Month();
    OrderTodayResponseDto getOrderToday();
    MoneyTotalTodayResponseDto getMoneyTotalToday();

    OrderQuantityNeedAllowResponseDto getOrderQuantityNeedAllow();

    List<AllOrderResponseDto> getAllOrder();
    List<AllOrderResponseDto> getAllOrderByOrderId(Long orderId);

    OrderQuantityResponseDto getOrderQuantity();

    void acceptOrder(Long orderId, Long employeeId, String employeeName, String employeeAvatar);

    void denyOrder(Long orderId, Long employeeId, String employeeName, String employeeAvatar);

}
