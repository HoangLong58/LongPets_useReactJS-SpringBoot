package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
