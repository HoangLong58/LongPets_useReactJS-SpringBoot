package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeEmail(String employeeEmail);
}
