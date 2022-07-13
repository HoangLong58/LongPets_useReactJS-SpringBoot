package com.longpets.longpetsecommerce.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AddRoleToCustomerRequestDto {
    @NotNull(message = "customerEmail can't be empty")
    @Email(message = "customerEmail is not email form")
    private String customerEmail;

    @NotNull(message = "roleName can't be empty")
    private String roleName;
}
