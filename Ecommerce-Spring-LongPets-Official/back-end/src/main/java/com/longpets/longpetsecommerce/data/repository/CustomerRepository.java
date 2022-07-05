package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.dto.response.CustomerResponseDto;
import com.longpets.longpetsecommerce.dto.response.MessageResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerEmail(String customerEmail);

    @Query(value = "select customer_id, customer_email, customer_password, customer_name, customer_birthday, customer_gender, customer_phone, customer_address, customer_avatar, ward_id from customer where customer_id != 0 AND customer_id != ? AND customer_phone = ?;",
            nativeQuery = true)
    List<CustomerResponseDto> checkCustomerPhone(Long customerId, String customerPhone);
}
