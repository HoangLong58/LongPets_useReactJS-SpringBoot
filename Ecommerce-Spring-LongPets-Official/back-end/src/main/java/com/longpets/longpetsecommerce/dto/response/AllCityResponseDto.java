package com.longpets.longpetsecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.longpets.longpetsecommerce.data.model.District;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public interface AllCityResponseDto {

    @Value("#{target.city_id}")
    String getCityId();

    @Value("#{target.city_name}")
    String getCityName();

    @Value("#{target.city_type}")
    String getCityType();
}
