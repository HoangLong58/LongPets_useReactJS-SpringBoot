package com.longpets.longpetsecommerce.dto.response;

import com.longpets.longpetsecommerce.data.model.Pet;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {
    private Long categoryId;

    private String categoryName;

    private String categoryTitle;

    private String categoryImage;

    private List<Pet> pets;
}
