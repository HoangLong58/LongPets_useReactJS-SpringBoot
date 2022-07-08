package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.dto.response.AllPetSearchByNameResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetAndCategoryByPetIdResponseDto;
import com.longpets.longpetsecommerce.dto.response.PetNameAndCategoryTitleByCategoryIdResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, c.category_name from pet p join category c on p.category_id = c.category_id where p.pet_name like concat('%', ?1, '%') or c.category_name like concat('%', ?1, '%')",
            nativeQuery = true)
    List<AllPetSearchByNameResponseDto> getAllPetSearchByName(String petName);


    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, c.category_name, c.category_title, c.category_image from pet p join category c on p.category_id = c.category_id where p.pet_id = ?",
            nativeQuery = true)
    List<PetAndCategoryByPetIdResponseDto> getPetAndCategoryByPetId(Long petId);

    @Query(value = "select DISTINCT p.pet_name, c.category_title FROM pet p join category c on p.category_id = c.category_id WHERE p.category_id = ?;",
            nativeQuery = true)
    List<PetNameAndCategoryTitleByCategoryIdResponseDto> getPetNameAndCategoryTitleByCategoryId(Long categoryId);
}
