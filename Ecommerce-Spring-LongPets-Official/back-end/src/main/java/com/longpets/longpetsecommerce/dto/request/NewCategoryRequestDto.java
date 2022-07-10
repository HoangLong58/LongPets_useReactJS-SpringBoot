package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class NewCategoryRequestDto {
    private String categoryName;

    private String categoryTitle;

    private String categoryImage;
}
