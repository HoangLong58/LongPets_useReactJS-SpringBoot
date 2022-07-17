package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.dto.response.WardDistrictCityResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query(value = "select w.ward_id, w.ward_name, w.ward_type, d.district_id, d.district_name, d.district_type, c.city_id, c.city_name, c.city_type from ward w join district d on w.district_id = d.district_id join city c on d.city_id = c.city_id where w.ward_id = ?",
            nativeQuery = true)
    WardDistrictCityResponseDto getWardDistrictCity(String wardId);

//    =========================================== FIX ===============================================
//    List<Customer> findAll();
List<Customer> findAllByCustomerIdNot(Long customerId);

    Optional<Customer> findCustomerByCustomerId(Long customerId);

    List<Customer> findCustomerByCustomerNameContaining(String customerName);

    Optional<Customer> findCustomerByCustomerEmail(String customerEmail);
    List<Customer> findCustomersByCustomerPhone(String customerPhone);

}
