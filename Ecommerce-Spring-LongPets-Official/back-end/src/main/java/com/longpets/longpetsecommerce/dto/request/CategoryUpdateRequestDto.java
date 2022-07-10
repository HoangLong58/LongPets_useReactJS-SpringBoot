package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

@Data
public class CategoryUpdateRequestDto {
    private Long categoryId;

    private String categoryName;

    private String categoryTitle;

    private String categoryImage;
}
