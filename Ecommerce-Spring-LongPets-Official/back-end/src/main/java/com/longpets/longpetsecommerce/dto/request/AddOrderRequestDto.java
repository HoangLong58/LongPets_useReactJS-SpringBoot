package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AddOrderRequestDto {
    @NotNull(message = "customerId can't be empty")
    @Min(value = 0, message = "customerId can't be negative")
    private Long customerId;

    @NotNull(message = "wardId can't be empty")
    private String wardId;

    @NotNull(message = "orderName can't be empty")
    private String orderName;

    @NotNull(message = "orderEmail can't be empty")
    @Email
    private String orderEmail;

    @NotNull(message = "orderPhone can't be empty")
    @Size(min = 10, max = 11, message = "orderPhone must have 10 to 11 number")
    private String orderPhone;

    @NotNull(message = "orderAddress can't be empty")
    private String orderAddress;

    @NotNull(message = "orderNote can't be empty")
    private String orderNote;

    @NotNull(message = "orderTotal can't be empty")
    @Min(value = 0)
    private Long orderTotal;

    @NotNull(message = "cart can't be empty")
    private List<AddOrderCartRequestDto> cart;
}
