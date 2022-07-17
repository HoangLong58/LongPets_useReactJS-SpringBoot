package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AdminRegisterRequestDto {
    @NotNull(message = "employeeName can't be empty")
    private String employeeName;

    @NotNull(message = "employeeEmail can't be empty")
    @Email(message = "employeeEmail is not email form")
    private String employeeEmail;

    @NotNull(message = "employeePassword can't be empty")
    @Size(min = 8, max = 30, message = "employeePassword must have more than 8 character")
    private String employeePassword;
}
