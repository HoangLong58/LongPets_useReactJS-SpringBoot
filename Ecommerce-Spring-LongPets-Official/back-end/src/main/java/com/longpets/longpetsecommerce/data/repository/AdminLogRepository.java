package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.AdminLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {

}
