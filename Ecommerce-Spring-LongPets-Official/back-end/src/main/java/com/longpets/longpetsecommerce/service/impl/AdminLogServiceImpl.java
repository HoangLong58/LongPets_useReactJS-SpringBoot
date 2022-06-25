package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.dto.request.AdminLogUpdateDto;
import com.longpets.longpetsecommerce.dto.response.AdminLogResponseDto;
import com.longpets.longpetsecommerce.data.model.AdminLog;
import com.longpets.longpetsecommerce.data.repository.AdminLogRepository;
import com.longpets.longpetsecommerce.service.AdminLogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLogServiceImpl implements AdminLogService {
    private AdminLogRepository adminLogRepository;

    private ModelMapper modelMapper;

    @Autowired
    public AdminLogServiceImpl(AdminLogRepository adminLogRepository, ModelMapper modelMapper) {
        this.adminLogRepository = adminLogRepository;
        this.modelMapper = modelMapper;
    }

//    Get all admin_log
    @Override
    public List<AdminLog> getAllAdminLogs() {
        return this.adminLogRepository.findAll();
    }

    @Override
    public AdminLogResponseDto createAdminLog(AdminLogUpdateDto dto) {
        AdminLog adminLog = modelMapper.map(dto, AdminLog.class);
        AdminLog savedAdminLog = adminLogRepository.save(adminLog);
        return modelMapper.map(savedAdminLog, AdminLogResponseDto.class);
    }
}
