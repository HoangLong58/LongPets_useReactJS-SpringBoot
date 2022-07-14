package com.longpets.longpetsecommerce.dto.response;

import com.longpets.longpetsecommerce.data.model.Order;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.data.model.Ward;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {
    private Long customerId;

    private String customerEmail;

    private String customerPassword;

    private String customerName;

    private Date customerBirthday;

    private String customerGender;

    private String customerPhone;

    private String customerAddress;

    private String customerAvatar;

    private Ward wardCustomer;

    private Collection<Role> roles = new ArrayList<>();

    private List<Order> orders;
}
