package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.dto.request.AdminLogUpdateDto;
import com.longpets.longpetsecommerce.dto.response.AdminLogResponseDto;
import com.longpets.longpetsecommerce.data.model.AdminLog;

import java.util.List;

public interface AdminLogService {
    public List<AdminLogResponseDto> getAllAdminLogs();

    public AdminLogResponseDto createAdminLog(AdminLogUpdateDto dto);
}
