package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.City;
import com.longpets.longpetsecommerce.dto.response.AllCityResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    @Query(value = "select city_id, city_name, city_type from city;",
            nativeQuery = true)
    List<AllCityResponseDto> getAllCity();
}
