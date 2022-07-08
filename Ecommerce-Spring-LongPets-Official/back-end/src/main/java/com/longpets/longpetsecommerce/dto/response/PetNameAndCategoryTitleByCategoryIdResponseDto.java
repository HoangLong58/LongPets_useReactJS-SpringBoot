package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface PetNameAndCategoryTitleByCategoryIdResponseDto {

    @Value("#{target.pet_name}")
    String getPetName();

    @Value("#{target.category_title}")
    String getCategoryTitle();
}
