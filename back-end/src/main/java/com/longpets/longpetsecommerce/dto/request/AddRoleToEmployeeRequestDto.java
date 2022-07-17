package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AddRoleToEmployeeRequestDto {
    @NotNull(message = "employeeEmail can't be empty")
    @Email(message = "employeeEmail is not email form")
    private String employeeEmail;

    @NotNull(message = "roleName can't be empty")
    private String roleName;
}
