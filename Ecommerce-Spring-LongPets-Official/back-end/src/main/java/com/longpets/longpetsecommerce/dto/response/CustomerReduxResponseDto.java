package com.longpets.longpetsecommerce.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerReduxResponseDto {
    private Long customerId;

    private String customerEmail;

    private String customerPassword;

    private String customerName;

    private Date customerBirthday;

    private String customerGender;

    private String customerPhone;

    private String customerAddress;

    private String customerAvatar;

    private String wardId;
}
