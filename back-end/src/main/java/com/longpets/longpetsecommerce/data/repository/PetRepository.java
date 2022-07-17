package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.dto.response.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update pet set category_id = ?, pet_name = ?, pet_gender =?, pet_age = ?, pet_vaccinated = ?, pet_health_warranty = ?, pet_title = ?, pet_description = ?, pet_note = ?, pet_quantity = ?, pet_price = ?, pet_price_discount = ? where pet_id = ?;",
            nativeQuery = true)
    void updatePet(Long categoryId, String petName, String petGender, String petAge, String petVaccinated, String petHealthWarranty, String petTitle, String petDescription, String petNote, Long petQuantity, Long petPrice, Long petPriceDiscount, Long petId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from image where pet_id = ?;",
            nativeQuery = true)
    void deletePetImage(Long petId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into image (pet_id, image_content) values (?, ?);",
            nativeQuery = true)
    void addPetImage(Long petId, String imageContent);

    @Query(value = "select pet_id from pet where category_id = ? AND pet_name = ? AND pet_gender = ?  AND pet_age = ? AND pet_vaccinated = ? AND pet_health_warranty = ? AND pet_title = ? AND pet_description = ? AND pet_note = ? AND pet_quantity = ? AND pet_price = ? AND pet_price_discount = ?;",
            nativeQuery = true)
    FindPetIdResponseDto findPetId(Long categoryId, String petName, String petGender, String petAge, String petVaccinated, String petHealthWarranty, String petTitle, String petDescription, String petNote, Long petQuantity, Long petPrice, Long petPriceDiscount);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from pet where pet_id = ?;",
            nativeQuery = true)
    void deletePet(Long petId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from order_detail where pet_id = ?;",
            nativeQuery = true)
    void deletePetOrderDetail(Long petId);

//    =================================== FIX =====================================
    List<Pet> findAll();

    List<Pet> findPetByPetNameContaining(String petName);

    Optional<Pet> findPetByPetId(Long petId);

    List<Pet> findPetByCategoryPet(Category category);
}
