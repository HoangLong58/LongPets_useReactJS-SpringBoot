package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface AllDetailOrderByOrderIdResponseDto {

    @Value("#{target.order_detail_id}")
    Long getOrderDetailId();

    @Value("#{target.order_detail_price}")
    Long getOrderDetailPrice();

    @Value("#{target.order_detail_quantity}")
    Long getOrderDetailQuantity();

    @Value("#{target.order_detail_total}")
    Long getOrderDetailTotal();

    @Value("#{target.pet_id}")
    Long getPetId();

    @Value("#{target.order_id}")
    Long getOrderId();
}
