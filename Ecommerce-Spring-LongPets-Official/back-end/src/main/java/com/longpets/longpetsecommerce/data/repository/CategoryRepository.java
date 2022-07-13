package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.dto.response.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, min(c.category_name) as category_name, min(c.category_title) as category_title, min(c.category_image) as category_image, min(i.image_id) as image_id, min(i.image_content) as image_content" +
//            " from pet p join category c" +
//            " on p.category_id = c.category_id" +
//            " join image i" +
//            " on p.pet_id = i.pet_id" +
//            " where c.category_id = ?" +
//            " group by p.pet_id;",
//            nativeQuery = true)
//    List<AllPetOfCategoryResponseDto> getAllPetOfCategoryByCategoryId(Long categoryId);

//    @Query(value = "select p.pet_id, p.pet_name, p.pet_gender, p.pet_age, p.pet_vaccinated, p.pet_health_warranty, p.pet_title, p.pet_description, p.pet_note, p.pet_quantity, p.pet_price, p.pet_price_discount, p.category_id, min(c.category_name) as category_name, min(c.category_title) as category_title, min(c.category_image) as category_image, min(i.image_id) as image_id, min(i.image_content) as image_content" +
//            " from pet p join category c" +
//            " on p.category_id = c.category_id" +
//            " join image i" +
//            " on p.pet_id = i.pet_id" +
//            " group by p.pet_id;",
//            nativeQuery = true)
//    List<AllPetOfCategoryResponseDto> getAllPetOfCategory();

//    @Query(value = "select category_id, category_name, category_title, category_image from category where category_name like concat('%', ?, '%')",
//            nativeQuery = true)
//    List<CategoryFindByCategoryNameResponseDto> getAllCategoryByName(String categoryName);

    @Query(value = "select count(category_id) as category_quantity from category",
            nativeQuery = true)
    CategoryQuantityResponseDto getCategoryQuantity();

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "update category set category_name = ?, category_title = ?, category_image = ? where category_id = ?",
//            nativeQuery = true)
//    void updateCategory(String categoryName, String categoryTitle, String categoryImage, Long categoryId);
//
//    @Query(value = "select category_id, category_name, category_title, category_image from category where category_id = ?",
//            nativeQuery = true)
//    List<CategoryFindByCategoryIdResponseDto> findCategoryByCategotyId(Long categoryId);
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "insert into category (category_name, category_title, category_image) values (?, ?, ?);",
//            nativeQuery = true)
//    void addCategory(String categoryName, String categoryTitle, String categoryImage);
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "delete from category where category_id = ?;",
//            nativeQuery = true)
//    void deleteCategory(Long categoryId);

//    ==============================================
    List<Category> findAll();

    Optional<Category> findCategoryByCategoryId(Long categoryId);

    List<Category> findCategoryByCategoryNameContaining(String categoryName);
}