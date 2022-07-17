package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterRequestDto {
    @NotNull(message = "customerName can't be empty")
    private String customerName;

    @NotNull(message = "customerEmail can't be empty")
    @Email(message = "customerEmail is not email form")
    private String customerEmail;

    @NotNull(message = "customerPassword can't be empty")
    @Size(min = 8, max = 30, message = "customerPassword must have more than 8 character")
    private String customerPassword;
}
