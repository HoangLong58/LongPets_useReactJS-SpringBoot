package com.longpets.longpetsecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetNameResponseDto {
    private String petName;

    private String categoryTitle;
}
