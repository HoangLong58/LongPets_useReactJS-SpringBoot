package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public interface EmployeeResponseDto {
    @Value("#{target.employee_id}")
    Long getEmployeeId();

    @Value("#{target.employee_email}")
    String getEmployeeEmail();

    @Value("#{target.employee_password}")
    String getEmployeePassword();

    @Value("#{target.employee_name}")
    String getEmployeeName();

    @Value("#{target.employee_birthday}")
    Date getEmployeeBirthday();

    @Value("#{target.employee_gender}")
    String getEmployeeGender();

    @Value("#{target.employee_phone}")
    String getEmployeePhone();

    @Value("#{target.employee_address}")
    String getEmployeeAddress();

    @Value("#{target.employee_avatar}")
    String getEmployeeAvatar();

    @Value("#{target.ward_id}")
    String getWardId();

}
