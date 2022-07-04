package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.OrderDetail;
import com.longpets.longpetsecommerce.dto.response.AllPetOfOrderDetailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
