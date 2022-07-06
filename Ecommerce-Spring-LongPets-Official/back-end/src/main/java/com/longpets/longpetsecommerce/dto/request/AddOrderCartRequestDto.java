package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class AddOrderCartRequestDto {
    private Long petId;

    private Long petPriceDiscount;

    private Long petQuantityBuy;
}
