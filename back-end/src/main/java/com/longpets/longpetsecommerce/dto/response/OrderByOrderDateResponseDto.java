package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface OrderByOrderDateResponseDto {
    @Value("#{target.order_id}")
    Long getOrderId();

    @Value("#{target.order_name}")
    String getOrderName();

    @Value("#{target.order_email}")
    String getOrderEmail();

    @Value("#{target.order_phone}")
    String getOrderPhone();

    @Value("#{target.order_address}")
    String getOrderAddress();

    @Value("#{target.order_note}")
    String getOrderNote();

    @Value("#{target.order_date}")
    Date getOrderDate();

    @Value("#{target.order_total}")
    Long getOrderTotal();

    @Value("#{target.customer_id}")
    Long getCustomerId();

    @Value("#{target.ward_id}")
    String  getWardId();

    @Value("#{target.employee_id}")
    Long getEmployeeId();

    @Value("#{target.order_status_id}")
    Long getOrderStatusId();
}
