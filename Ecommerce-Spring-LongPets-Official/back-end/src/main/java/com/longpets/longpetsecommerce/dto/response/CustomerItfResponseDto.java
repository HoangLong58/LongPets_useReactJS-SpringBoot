package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public interface CustomerItfResponseDto {

    @Value("#{target.customer_id}")
    Long getCustomerId();

    @Value("#{target.customer_email}")
    String getCustomerEmail();

    @Value("#{target.customer_password}")
    String getCustomerPassword();

    @Value("#{target.customer_name}")
    String getCustomerName();

    @Value("#{target.customer_birthday}")
    Date getCustomerBirthday();

    @Value("#{target.customer_gender}")
    String getCustomerGender();

    @Value("#{target.customer_phone}")
    String getCustomerPhone();

    @Value("#{target.customer_address}")
    String getCustomerAddress();

    @Value("#{target.customer_avatar}")
    String getCustomerAvatar();

    @Value("#{target.ward_id}")
    String getWardId();

}
