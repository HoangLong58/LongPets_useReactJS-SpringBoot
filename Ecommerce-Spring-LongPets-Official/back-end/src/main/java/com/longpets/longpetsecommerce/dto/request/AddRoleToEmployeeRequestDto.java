package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class AddRoleToEmployeeRequestDto {
    private String employeeEmail;
    private String roleName;
}
