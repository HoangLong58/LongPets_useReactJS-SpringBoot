package com.longpets.longpetsecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Image;
import com.longpets.longpetsecommerce.data.model.OrderDetail;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public interface AllPetSearchByNameResponseDto {

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
}
