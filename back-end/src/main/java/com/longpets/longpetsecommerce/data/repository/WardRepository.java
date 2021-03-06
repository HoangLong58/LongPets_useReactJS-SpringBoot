package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.dto.response.AllWardByDistrictIdResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.longpets.longpetsecommerce.data.model.Ward;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {

    @Query(value = "select ward_id, ward_name, ward_type, district_id from ward where district_id = ?;",
            nativeQuery = true)
    List<AllWardByDistrictIdResponseDto> getAllWardByDistrictId(String districtId);
}
