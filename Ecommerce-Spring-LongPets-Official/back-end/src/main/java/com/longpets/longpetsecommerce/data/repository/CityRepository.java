package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
