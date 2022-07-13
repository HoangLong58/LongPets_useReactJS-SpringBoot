package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdatePetRequestDto {
    @NotNull(message = "petId can't be empty")
    @Min(value = 0, message = "petId can't be negative")
    private Long petId;

    @NotNull(message = "categoryId can't be empty")
    @Min(value = 0, message = "categoryId can't be negative")
    private Long categoryId;

    @NotNull(message = "petName can't be empty")
    private String petName;

    @NotNull(message = "petGender can't be empty")
    private String petGender;

    @NotNull(message = "petAge can't be empty")
    private String petAge;

    @NotNull(message = "petVaccinated can't be empty")
    private String petVaccinated;

    @NotNull(message = "petHealthWarranty can't be empty")
    private String petHealthWarranty;

    @NotNull(message = "petTitle can't be empty")
    private String petTitle;

    @NotNull(message = "petDescription can't be empty")
    private String petDescription;

    @NotNull(message = "petNote can't be empty")
    private String petNote;

    @NotNull(message = "petQuantity can't be empty")
    @Min(value = 0, message = "petQuantity can't be negative")
    private Long petQuantity;

    @NotNull(message = "petPrice can't be empty")
    @Min(value = 0, message = "petPrice can't be negative")
    private Long petPrice;

    @NotNull(message = "petPriceDiscount can't be empty")
    @Min(value = 0, message = "petPriceDiscount can't be negative")
    private Long petPriceDiscount;

    @NotNull(message = "petImage can't be empty")
    private List<String> petImage;
}
