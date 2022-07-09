package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class AdminRegisterRequestDto {
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;
}
