package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.Employee;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.dto.request.AdminRegisterRequestDto;
import com.longpets.longpetsecommerce.dto.response.EmployeeResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface EmployeeService {

//    ADMIN:
    Employee saveEmployee(Employee employee);
    Role saveRole(Role role);
    void addRoleToEmployee(String emailEmployee, String roleName);
    Employee getEmployee(String emailEmployee);
    List<Employee> getEmployees();

    Employee registerEmployee(AdminRegisterRequestDto employee);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    EmployeeResponseDto getEmployeeByEmail(String employeeEmail);
//    ===========
}
