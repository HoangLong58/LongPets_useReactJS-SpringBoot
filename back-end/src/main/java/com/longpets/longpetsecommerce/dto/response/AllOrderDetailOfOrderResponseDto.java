package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public interface AllOrderDetailOfOrderResponseDto {
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

    @Value("#{target.order_status_id}")
    Long getOrderStatusId();

    @Value("#{target.order_status_name}")
    String getOrderStatusName();

    @Value("#{target.employee_id}")
    Long getEmployeeId();

    @Value("#{target.employee_email}")
    String getEmployeeEmail();

    @Value("#{target.employee_password}")
    String getEmployeePassword();

    @Value("#{target.employee_name}")
    String getEmployeeName();

    @Value("#{target.employee_birthday}")
    Date getEmployeeBirthday();

    @Value("#{target.employee_gender}")
    String getEmployeeGender();

    @Value("#{target.employee_phone}")
    String getEmployeePhone();

    @Value("#{target.employee_address}")
    String getEmployeeAddress();

    @Value("#{target.employee_avatar}")
    String getEmployeeAvatar();

    @Value("#{target.ward_id}")
    String getWardId();

    @Value("#{target.ward_name}")
    String getWardName();

    @Value("#{target.ward_type}")
    String getWardType();

    @Value("#{target.district_id}")
    String getDistrictId();

    @Value("#{target.district_name}")
    String getDistrictName();

    @Value("#{target.district_type}")
    String getDistrictType();

    @Value("#{target.city_id}")
    String getCityId();

    @Value("#{target.city_name}")
    String getCityName();

    @Value("#{target.city_type}")
    String getCityType();
}
