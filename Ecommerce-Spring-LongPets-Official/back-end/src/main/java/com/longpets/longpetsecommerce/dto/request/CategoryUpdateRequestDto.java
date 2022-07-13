package com.longpets.longpetsecommerce.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CategoryUpdateRequestDto {
    @NotNull(message = "categoryName can't be empty")
    private String categoryName;

    @NotNull(message = "categoryTitle can't be empty")
    private String categoryTitle;

    @NotNull(message = "categoryImage can't be empty")
    private String categoryImage;
}
