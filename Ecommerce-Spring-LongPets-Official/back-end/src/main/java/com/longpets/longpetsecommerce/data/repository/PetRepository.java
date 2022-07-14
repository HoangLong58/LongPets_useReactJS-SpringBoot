package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Image;
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
//    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, c.category_name from pet p join category c on p.category_id = c.category_id where p.pet_name like concat('%', ?1, '%') or c.category_name like concat('%', ?1, '%')",
//            nativeQuery = true)
//    List<AllPetSearchByNameResponseDto> getAllPetSearchByName(String petName);

//    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, c.category_name, c.category_title, c.category_image from pet p join category c on p.category_id = c.category_id where p.pet_id = ?",
//            nativeQuery = true)
//    List<PetAndCategoryByPetIdResponseDto> getPetAndCategoryByPetId(Long petId);

//    @Query(value = "select DISTINCT p.pet_name, c.category_title FROM pet p join category c on p.category_id = c.category_id WHERE p.category_id = ?;",
//            nativeQuery = true)
//    List<PetNameAndCategoryTitleByCategoryIdResponseDto> getPetNameAndCategoryTitleByCategoryId(Long categoryId);

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

//    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, c.category_name, c.category_title, c.category_image, min(i.image_content) as image_content from pet p join category c on p.category_id = c.category_id join image i on p.pet_id = i.pet_id where p.pet_id = ? group by p.pet_id, c.category_id;",
//            nativeQuery = true)
//    List<PetAndCategoryAndImageByPetIdResponseDto> getPetAndCategoryAndImageByPetId(Long petId);

//    @Query(value = "select i.image_content from pet p join image i on p.pet_id = i.pet_id where p.pet_id = ?",
//            nativeQuery = true)
//    List<AllPetImageResponseDto> getAllPetImage(Long petId);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "insert into pet (category_id, pet_name, pet_gender, pet_age, pet_vaccinated, pet_health_warranty, pet_title, pet_description, pet_note, pet_quantity, pet_price, pet_price_discount) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
//            nativeQuery = true)
//    void addPet(Long categoryId, String petName, String petGender, String petAge, String petVaccinated, String petHealthWarranty, String petTitle, String petDescription, String petNote, Long petQuantity, Long petPrice, Long petPriceDiscount);

    @Query(value = "select pet_id from pet where category_id = ? AND pet_name = ? AND pet_gender = ?  AND pet_age = ? AND pet_vaccinated = ? AND pet_health_warranty = ? AND pet_title = ? AND pet_description = ? AND pet_note = ? AND pet_quantity = ? AND pet_price = ? AND pet_price_discount = ?;",
            nativeQuery = true)
    FindPetIdResponseDto findPetId(Long categoryId, String petName, String petGender, String petAge, String petVaccinated, String petHealthWarranty, String petTitle, String petDescription, String petNote, Long petQuantity, Long petPrice, Long petPriceDiscount);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from pet where pet_id = ?;",
            nativeQuery = true)
    void deletePet(Long petId);

//    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, c.category_name, c.category_title, c.category_image, min(i.image_content) as image_content from pet p join category c on p.category_id = c.category_id join image i on p.pet_id = i.pet_id group by p.pet_id, c.category_id",
//            nativeQuery = true)
//    List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImage();

//    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, c.category_name, c.category_title, c.category_image, min(i.image_content) as image_content from pet p join category c on p.category_id = c.category_id join image i on p.pet_id = i.pet_id where p.pet_name like concat('%', ?, '%') group by p.pet_id, c.category_id",
//            nativeQuery = true)
//    List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImageByPetName(String petName);

//    @Query(value = "select count(pet_id) as pet_quantity_count from pet",
//            nativeQuery = true)
//    PetQuantityCountResponseDto getPetQuantityCount();

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
