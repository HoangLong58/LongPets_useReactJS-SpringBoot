package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryFindByCategoryIdResponseDto {
    @Value("#{target.category_id}")
    Long getCategoryId();

    @Value("#{target.category_name}")
    String getCategoryName();

    @Value("#{target.category_title}")
    String getCategoryTitle();

    @Value("#{target.category_image}")
    String getCategoryImage();
}
