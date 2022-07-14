package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.data.repository.OrderRepository;
import com.longpets.longpetsecommerce.dto.request.AddOrderCartRequestDto;
import com.longpets.longpetsecommerce.dto.request.AddOrderRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
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

        orderRepository.updateUserCancelOrder(orderId);

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

        List<OrderByOrderDateResponseDto> orders = orderRepository.getOrderByOrderDate(
                orderDate,
                addOrderRequestDto.getCustomerId(),
                addOrderRequestDto.getOrderPhone());
        if (orders.isEmpty()) {
            throw new ApiRequestException("Error: Can't find order");
        }
        OrderByOrderDateResponseDto order = orders.get(0);
        List<AddOrderCartRequestDto> cartList = addOrderRequestDto.getCart();

        cartList.stream().forEach(cartItem -> {
            orderRepository.addOrderDetail(cartItem.getData().getPetId(),
                    order.getOrderId(),
                    cartItem.getData().getPetPriceDiscount(),
                    cartItem.getPetQuantityBuy(),
                    cartItem.getData().getPetPriceDiscount() * cartItem.getPetQuantityBuy());

            orderRepository.updatePetQuantityAfterOrder(cartItem.getPetQuantityBuy(), cartItem.getData().getPetId());

        });
    }

    @Override
    public List<OrderByCustomerIdResponseDto> getOrderByCustomerId(Long customerId) {
        return orderRepository.getOrderByCustomerId(customerId);
    }

    @Override
    public CategoryProfitResponseDto getDogProfit(Long day, Long month, Long year) {
        return orderRepository.getDogProfit(day, month, year);
    }

    @Override
    public CategoryProfitResponseDto getDogProfitYear() {
        return orderRepository.getDogProfitYear();
    }

    @Override
    public CategoryProfitResponseDto getCatProfit(Long day, Long month, Long year) {
        return orderRepository.getCatProfit(day, month, year);
    }

    @Override
    public CategoryProfitResponseDto getCatProfitYear() {
        return orderRepository.getCatProfitYear();
    }

    @Override
    public CategoryProfitResponseDto getAnotherProfit(Long day, Long month, Long year) {
        return orderRepository.getAnotherProfit(day, month, year);
    }

    @Override
    public CategoryProfitResponseDto getAnotherProfitYear() {
        return orderRepository.getAnotherProfitYear();
    }

    @Override
    public MoneyTotalResponseDto getMoneyTotal(Long day, Long month, Long year) {
        return orderRepository.getMoneyTotal(day, month, year);
    }

    @Override
    public MoneyTotalResponseDto getMoneyTotalYear() {
        return orderRepository.getMoneyTotalYear();
    }

    @Override
    public List<MoneyTotal12MonthResponseDto> getMoneyTotal12Month() {
        return orderRepository.getMoneyTotal12Month();
    }

    @Override
    public OrderTodayResponseDto getOrderToday() {
        return orderRepository.getOrderToday();
    }

    @Override
    public MoneyTotalTodayResponseDto getMoneyTotalToday() {
        return orderRepository.getMoneyTotalToday();
    }

    @Override
    public OrderQuantityNeedAllowResponseDto getOrderQuantityNeedAllow() {
        return orderRepository.getOrderQuantityNeedAllow();
    }

    @Override
    public List<AllOrderResponseDto> getAllOrder() {
        return orderRepository.getAllOrder();
    }

    @Override
    public List<AllOrderResponseDto> getAllOrderByOrderId(Long orderId) {
        return orderRepository.getAllOrderByOrderId(orderId);
    }

    @Override
    public OrderQuantityResponseDto getOrderQuantity() {
        return orderRepository.getOrderQuantity();
    }

    @Override
    public void acceptOrder(Long orderId, Long employeeId, String employeeName, String employeeAvatar) {

        orderRepository.acceptOrder(employeeId, orderId);

        orderRepository.addLog(employeeName, orderId, employeeAvatar);
    }

    @Override
    public void denyOrder(Long orderId, Long employeeId, String employeeName, String employeeAvatar) {

        orderRepository.denyOrder(employeeId, orderId);

        List<AllDetailOrderByOrderIdResponseDto> allDetailOrderByOrderIds = orderRepository.findAllDetailOrderByOrderId(orderId);

        allDetailOrderByOrderIds.stream().forEach(detailOrder -> {
            orderRepository.updatePetQuantityAfterDeny(detailOrder.getOrderDetailQuantity(), detailOrder.getPetId());
        });
        orderRepository.addLogDeny(employeeName, orderId, employeeAvatar);
    }
}
