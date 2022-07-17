package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
