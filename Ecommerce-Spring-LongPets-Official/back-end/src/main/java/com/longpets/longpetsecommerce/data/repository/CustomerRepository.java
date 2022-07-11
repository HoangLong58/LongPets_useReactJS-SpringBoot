package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.CustomerQuantityResponseDto;
import com.longpets.longpetsecommerce.dto.response.CustomerResponseDto;
import com.longpets.longpetsecommerce.dto.response.MessageResponseDto;
import com.longpets.longpetsecommerce.dto.response.WardDistrictCityResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerEmail(String customerEmail);

    @Query(value = "select customer_id, customer_email, customer_password, customer_name, customer_birthday, customer_gender, customer_phone, customer_address, customer_avatar, ward_id from customer where customer_id != 0 AND customer_id != ? AND customer_phone = ?;",
            nativeQuery = true)
    List<CustomerResponseDto> checkCustomerPhone(Long customerId, String customerPhone);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update customer set ward_id = ?, customer_name = ?, customer_birthday = ?, customer_gender = ?, customer_phone = ?, customer_address = ?, customer_avatar = ? where customer_id = ?;",
            nativeQuery = true)
    void updateCustomer(String wardId, String customerName, Date customerBirthday, String customerGender, String customerPhone, String customerAddress, String customerAvatar, Long customerId);

    @Query(value = "select customer_id, customer_email, customer_password, customer_name, customer_birthday, customer_gender, customer_phone, customer_address, customer_avatar, ward_id from customer where customer_id = ?;",
            nativeQuery = true)
    CustomerResponseDto findCustomerByCustomerId(Long customerId);

    @Query(value = "select customer_id, customer_email, customer_password, customer_name, customer_birthday, customer_gender, customer_phone, customer_address, customer_avatar, ward_id from customer where customer_email = ?;",
            nativeQuery = true)
    CustomerResponseDto findCustomerByCustomerEmail(String customerEmail);

    @Query(value = "select customer_id, customer_email, customer_password, customer_name, customer_birthday, customer_gender, customer_phone, customer_address, customer_avatar, ward_id from customer where customer_id != 0;",
            nativeQuery = true)
    List<CustomerResponseDto> getCustomer();

    @Query(value = "select customer_id, customer_email, customer_password, customer_name, customer_birthday, customer_gender, customer_phone, customer_address, customer_avatar, ward_id from customer where customer_name like concat('%', ?, '%') AND customer_id != 0;",
            nativeQuery = true)
    List<CustomerResponseDto> getCustomerByCustomerName(String customerName);

    @Query(value = "select count(customer_id) as customer_quantity from customer where customer_id != 0;",
            nativeQuery = true)
    CustomerQuantityResponseDto getCustomerQuantity();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from customer where customer_id = ?;",
            nativeQuery = true)
    void deleteCustomer(Long customerId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from customer_role where customer_id = ?;",
            nativeQuery = true)
    void deleteCustomerRole(Long customerId);

    @Query(value = "select w.ward_id, w.ward_name, w.ward_type, d.district_id, d.district_name, d.district_type, c.city_id, c.city_name, c.city_type from ward w join district d on w.district_id = d.district_id join city c on d.city_id = c.city_id where w.ward_id = ?",
            nativeQuery = true)
    WardDistrictCityResponseDto getWardDistrictCity(String wardId);

}
