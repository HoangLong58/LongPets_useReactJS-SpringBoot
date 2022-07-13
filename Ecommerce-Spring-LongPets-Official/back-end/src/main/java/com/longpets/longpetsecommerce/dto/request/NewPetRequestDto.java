package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class NewPetRequestDto {
    private Long categoryId;
    private String petAge;
    private String petDescription;
    private String petGender;
    private String petHealthWarranty;
    private String petName;
    private String petNote;
    private Long petPrice;
    private Long petPriceDiscount;
    private Long petQuantity;
    private String petTitle;
    private String petVaccinated;
    private List<String> petImage;
}
