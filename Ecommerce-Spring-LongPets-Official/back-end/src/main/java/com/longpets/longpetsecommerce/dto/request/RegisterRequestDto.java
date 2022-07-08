package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String customerName;
    private String customerEmail;
    private String customerPassword;
}
