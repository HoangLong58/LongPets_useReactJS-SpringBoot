package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
