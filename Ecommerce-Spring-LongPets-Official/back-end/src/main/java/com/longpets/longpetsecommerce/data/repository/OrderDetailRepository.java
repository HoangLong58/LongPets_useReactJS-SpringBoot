package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
