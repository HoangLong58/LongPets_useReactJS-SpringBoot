package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String customerEmail;
    private String customerPassword;
}
