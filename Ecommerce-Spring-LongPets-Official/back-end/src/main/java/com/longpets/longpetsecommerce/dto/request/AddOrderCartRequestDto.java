package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderCartRequestDto {
//    private Long petId;
//
//    private Long petPriceDiscount;
//
//    private Long petQuantityBuy;
    private List<PetAndCategoryRequestDto> data;
    private Long petQuantityBuy;
}
