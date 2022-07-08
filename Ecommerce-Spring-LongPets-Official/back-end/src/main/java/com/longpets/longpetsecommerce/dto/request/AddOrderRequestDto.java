package com.longpets.longpetsecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Data
public class AddOrderRequestDto {
    private Long customerId;

    private String wardId;

    private String orderName;

    private String orderEmail;

    private String orderPhone;

    private String orderAddress;

    private String orderNote;

    private Long orderTotal;

    private List<AddOrderCartRequestDto> cart;
}
