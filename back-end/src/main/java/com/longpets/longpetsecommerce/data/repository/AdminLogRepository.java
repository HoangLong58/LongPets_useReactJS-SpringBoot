package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.AdminLog;
import com.longpets.longpetsecommerce.dto.response.AdminLogResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {

    @Query(value = "SELECT log_id, log_content, log_avatar FROM admin_log order by log_id desc LIMIT 3;", nativeQuery = true)
    List<AdminLogResponseDto> find3AdminLog();
}
