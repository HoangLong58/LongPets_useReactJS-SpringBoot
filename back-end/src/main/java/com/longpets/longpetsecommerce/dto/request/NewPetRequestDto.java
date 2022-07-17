package com.longpets.longpetsecommerce.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPetRequestDto {
    @NotNull(message = "categoryId can't be empty")
    @Min(value = 0, message = "categoryId can't be negative")
    private Long categoryId;

    @NotNull(message = "petAge can't be empty")
    private String petAge;

    @NotNull(message = "petDescription can't be empty")
    private String petDescription;

    @NotNull(message = "petGender can't be empty")
    private String petGender;

    @NotNull(message = "petHealthWarranty can't be empty")
    private String petHealthWarranty;

    @NotNull(message = "petName can't be empty")
    private String petName;

    @NotNull(message = "petNote can't be empty")
    private String petNote;

    @NotNull(message = "petPrice can't be empty")
    @Min(value = 0, message = "petPrice can't be negative")
    private Long petPrice;

    @NotNull(message = "petPriceDiscount can't be empty")
    @Min(value = 0, message = "petPriceDiscount can't be negative")
    private Long petPriceDiscount;

    @NotNull(message = "petQuantity can't be empty")
    @Min(value = 0, message = "petQuantity can't be negative")
    private Long petQuantity;

    @NotNull(message = "petTitle can't be empty")
    private String petTitle;

    @NotNull(message = "petVaccinated can't be empty")
    private String petVaccinated;

    @NotNull(message = "petImage can't be empty")
    private List<String> petImage;
}
