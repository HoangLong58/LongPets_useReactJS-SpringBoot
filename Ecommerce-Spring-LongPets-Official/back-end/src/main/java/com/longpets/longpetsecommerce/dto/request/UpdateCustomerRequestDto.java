package com.longpets.longpetsecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.longpets.longpetsecommerce.data.model.Order;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.data.model.Ward;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Data
public class UpdateCustomerRequestDto {

    private Long customerId;

    private String customerEmail;

    private String customerPassword;

    private String customerName;

    private Date customerBirthday;

    private String customerGender;

    private String customerPhone;

    private String customerAddress;

    private String customerAvatar;

    private String wardId;
}
