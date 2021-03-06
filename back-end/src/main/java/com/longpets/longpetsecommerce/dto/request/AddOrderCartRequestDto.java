package com.longpets.longpetsecommerce.dto.request;

import com.longpets.longpetsecommerce.dto.response.PetResponseDto;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddOrderCartRequestDto {

    @NotNull(message = "Data can't be empty")
    private PetResponseDto data;

    @NotNull(message = "petQuantityBuy can't be empty")
    @Min(value = 1, message = "petQuantityBuy can't smaller than 1")
    private Long petQuantityBuy;
}
