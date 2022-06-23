package com.longpets.longpetsecommerce.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.longpets.longpetsecommerce.data.model.Ward;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {
    @Query(value = "select * from ward",
            nativeQuery = true)
    List<Ward> findAllWard();
}
