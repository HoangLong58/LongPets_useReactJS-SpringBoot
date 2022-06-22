package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.dto.request.AdminLogUpdateDto;
import com.longpets.longpetsecommerce.dto.response.AdminLogResponseDto;
import com.longpets.longpetsecommerce.data.model.AdminLog;
import com.longpets.longpetsecommerce.service.AdminLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/adminlog")
public class AdminLogController {
    private AdminLogService adminLogService;

    @GetMapping
    List<AdminLog> getAllAdminLogs() {
        return this.adminLogService.getAllAdminLogs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AdminLogResponseDto createAdminLog(@Valid @RequestBody AdminLogUpdateDto dto) {
        return this.adminLogService.createAdminLog(dto);
    }

    @Autowired
    public AdminLogController(AdminLogService adminLogService) {
        this.adminLogService = adminLogService;
    }
}
