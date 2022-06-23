package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.dto.response.WardResponeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, String>{

    @Query(value = "select * from district"
            ,nativeQuery = true)
    List<District> findAllDistrict();

    @Query(value = "select w.ward_id as wardId, w.ward_name as wardName, w.ward_type as wardType from district d join ward w on d.district_id = w.district_id  where d.district_id = ?1"
            ,nativeQuery = true)
    List<Ward> findAllWardByDistrictId(String districtId);
}
