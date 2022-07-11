package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdatePetRequestDto {
    private Long petId;
    private Long categoryId;
    private String petName;
    private String petGender;
    private String petAge;
    private String petVaccinated;
    private String petHealthWarranty;
    private String petTitle;
    private String petDescription;
    private String petNote;
    private Long petQuantity;
    private Long petPrice;
    private Long petPriceDiscount;
    private List<String> petImage;
}
