package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.dto.response.AllDistrictByCityIdResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, String> {

    @Query(value = "select district_id, district_name, district_type, city_id from district where city_id = ?;",
        nativeQuery = true)
    List<AllDistrictByCityIdResponseDto> getAllDistrictByCityId(String cityId);
}
