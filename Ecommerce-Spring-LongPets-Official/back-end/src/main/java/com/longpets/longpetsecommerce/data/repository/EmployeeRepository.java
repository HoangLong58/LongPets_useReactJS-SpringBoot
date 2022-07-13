package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Employee;
import com.longpets.longpetsecommerce.dto.response.EmployeeResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeEmail(String employeeEmail);

    @Query(value = "select employee_id, employee_email, employee_password, employee_name, employee_birthday, employee_gender, employee_phone, employee_address, employee_avatar, ward_id from employee where employee_email = ?",
            nativeQuery = true)
    EmployeeResponseDto getEmployeeByEmail(String employeeEmail);
}
