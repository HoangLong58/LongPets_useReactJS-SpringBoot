package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.District;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.dto.response.AllDistrictByCityIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.FindAllWardByDistrictIdResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
////    Get all district
//    @Query(value = "select * from district",
//            nativeQuery = true)
//    List<District> findAllDistrict();
//
////    Get ward by district_id
//    @Query(value = "select d.district_id as districtId, " +
//            "d.district_name as districtName, " +
//            "w.ward_id as wardId, " +
//            "w.ward_name as wardName, " +
//            "w.ward_type as wardType " +
//            "from district d join ward w " +
//            "on d.district_id = w.district_id " +
//            "where d.district_id = ?1",
//            nativeQuery = true)
//    List<FindAllWardByDistrictIdResponseDto> findAllWardByDistrictId(String districtId);

    @Query(value = "select district_id, district_name, district_type, city_id from district where city_id = ?;",
        nativeQuery = true)
    List<AllDistrictByCityIdResponseDto> getAllDistrictByCityId(String cityId);
}
