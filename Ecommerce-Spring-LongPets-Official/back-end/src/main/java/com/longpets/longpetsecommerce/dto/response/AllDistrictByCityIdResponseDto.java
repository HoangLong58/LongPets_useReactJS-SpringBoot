package com.longpets.longpetsecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.longpets.longpetsecommerce.data.model.City;
import com.longpets.longpetsecommerce.data.model.Ward;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public interface AllDistrictByCityIdResponseDto {
    @Value("#{target.district_id}")
    String getDistrictId();

    @Value("#{target.district_name}")
    String getDistrictName();

    @Value("#{target.district_type}")
    String getDistrictType();

    @Value("#{target.city_id}")
    String getCityId();
}
