package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class PetAndCategoryRequestDto {
    private Long categoryId;
    private String categoryImage;
    private String categoryName;
    private String categoryTitle;
    private String petAge;
    private String petDescription;
    private String petGender;
    private String petHealthWarranty;
    private Long petId;
    private String petName;
    private String petNote;
    private Long petPrice;
    private Long petPriceDiscount;
    private Long petQuantity;
    private String petTitle;
    private String petVaccinated;
}
