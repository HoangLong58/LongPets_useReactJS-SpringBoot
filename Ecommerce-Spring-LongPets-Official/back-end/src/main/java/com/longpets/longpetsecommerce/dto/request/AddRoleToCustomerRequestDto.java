package com.longpets.longpetsecommerce.dto.request;

import lombok.*;

@Data
public class AddRoleToCustomerRequestDto {
    private String customerEmail;
    private String roleName;
}
