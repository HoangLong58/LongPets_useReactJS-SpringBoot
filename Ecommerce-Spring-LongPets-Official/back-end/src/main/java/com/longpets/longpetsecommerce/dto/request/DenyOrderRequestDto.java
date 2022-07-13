package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DenyOrderRequestDto {
    @NotNull(message = "orderId can't be empty")
    @Min(value = 0, message = "orderId can't be negative")
    private Long orderId;

    @NotNull(message = "employeeId can't be empty")
    @Min(value = 0, message = "employeeId can't be negative")
    private Long employeeId;

    @NotNull(message = "employeeName can't be empty")
    private String employeeName;

    @NotNull(message = "employeeAvatar can't be empty")
    private String employeeAvatar;
}
