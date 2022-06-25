package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
