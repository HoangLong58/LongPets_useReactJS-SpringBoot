package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface PetAndCategoryAndImageByPetIdResponseDto {
    @Value("#{target.pet_id}")
    Long getPetId();

    @Value("#{target.pet_name}")
    String getPetName();

    @Value("#{target.pet_gender}")
    String getPetGender();

    @Value("#{target.pet_age}")
    String getPetAge();

    @Value("#{target.pet_vaccinated}")
    String getPetVaccinated();

    @Value("#{target.pet_health_warranty}")
    String getPetHealthWarranty();

    @Value("#{target.pet_title}")
    String getPetTitle();

    @Value("#{target.pet_description}")
    String getPetDescription();

    @Value("#{target.pet_note}")
    String getPetNote();

    @Value("#{target.pet_quantity}")
    Long getPetQuantity();

    @Value("#{target.pet_price}")
    Long getPetPrice();

    @Value("#{target.pet_price_discount}")
    Long getPetPriceDiscount();

    @Value("#{target.category_id}")
    Long getCategoryId();

    @Value("#{target.category_name}")
    String getCategoryName();

    @Value("#{target.category_title}")
    String getCategoryTitle();

    @Value("#{target.category_image}")
    String getCategoryImage();

    @Value("#{target.image_content}")
    String getImageContent();
}
