package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
