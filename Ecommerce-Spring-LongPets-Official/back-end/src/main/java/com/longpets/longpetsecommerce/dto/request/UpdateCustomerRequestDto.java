package com.longpets.longpetsecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.longpets.longpetsecommerce.data.model.Order;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.data.model.Ward;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerRequestDto {
    @NotNull(message = "customerId can't be empty")
    @Min(value = 0, message = "customerId can't be negative")
    private Long customerId;

    @NotNull(message = "customerEmail can't be empty")
    @Email(message = "customerEmail is not email form")
    private String customerEmail;

    @NotNull(message = "customerPassword can't be empty")
    @Size(min = 8, max = 30, message = "customerPassword must have more than 8 character")
    private String customerPassword;

    @NotNull(message = "customerName can't be empty")
    private String customerName;

    @NotNull(message = "customerBirthday can't be empty")
    private Date customerBirthday;

    @NotNull(message = "customerGender can't be empty")
    private String customerGender;

    @NotNull(message = "customerPhone can't be empty")
    @Size(min = 10, max = 11, message = "customerPhone must have 10 to 11 number")
    private String customerPhone;

    @NotNull(message = "customerAddress can't be empty")
    private String customerAddress;

    @NotNull(message = "customerAvatar can't be empty")
    private String customerAvatar;

    @NotNull(message = "wardId can't be empty")
    private String wardId;
}
