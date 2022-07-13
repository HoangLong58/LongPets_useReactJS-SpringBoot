package com.longpets.longpetsecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Image;
import com.longpets.longpetsecommerce.data.model.OrderDetail;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetResponseDto {
    private Long petId;

    private String petName;

    private String petGender;

    private String petAge;

    private String petVaccinated;

    private String petHealthWarranty;

    private String petTitle;

    private String petDescription;

    private String petNote;

    private Long petQuantity;

    private Long petPrice;

    private Long petPriceDiscount;

    private Category categoryPet;

    private List<OrderDetail> orderDetails;

    private List<Image> images;
}
