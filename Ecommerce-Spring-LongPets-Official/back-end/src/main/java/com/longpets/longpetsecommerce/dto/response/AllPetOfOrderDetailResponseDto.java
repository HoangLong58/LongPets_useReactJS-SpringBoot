package com.longpets.longpetsecommerce.dto.response;

import com.longpets.longpetsecommerce.data.model.Order;
import com.longpets.longpetsecommerce.data.model.Pet;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

public interface AllPetOfOrderDetailResponseDto {
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

    @Value("#{target.order_detail_id}")
    Long getOrderDetailId();

    @Value("#{target.order_detail_price}")
    Long getOrderDetailPrice();

    @Value("#{target.order_detail_quantity}")
    Long getOrderDetailQuantity();

    @Value("#{target.order_detail_total}")
    Long getOrderDetailTotal();
}
