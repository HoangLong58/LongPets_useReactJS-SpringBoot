package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    ==============================================
    List<Category> findAll();

    Optional<Category> findCategoryByCategoryId(Long categoryId);

    List<Category> findCategoryByCategoryNameContaining(String categoryName);
}