package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.data.model.Employee;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.dto.request.AddRoleToEmployeeRequestDto;
import com.longpets.longpetsecommerce.dto.request.AdminRegisterRequestDto;
import com.longpets.longpetsecommerce.dto.response.EmployeeResponseDto;
import com.longpets.longpetsecommerce.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/users")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok().body(employeeService.getEmployees());
    }

    @PostMapping("/user/save")
    public ResponseEntity<Employee>saveEmployee(@RequestBody Employee employee) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/employee/user/save").toUriString());
        return ResponseEntity.created(uri).body(employeeService.saveEmployee(employee));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/employee/role/save").toUriString());
        return ResponseEntity.created(uri).body(employeeService.saveRole(role));
    }

    @PostMapping("/role/add-to-user")
    public ResponseEntity<?>addRoleToUser(@Valid @RequestBody AddRoleToEmployeeRequestDto roleToUserForm) {
        employeeService.addRoleToEmployee(roleToUserForm.getEmployeeEmail(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?>registerEmployee(@Valid @RequestBody AdminRegisterRequestDto employee) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(employeeService.registerEmployee(employee));
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        employeeService.refreshToken(request, response);
    }

    @GetMapping("/get-employee-by-email/{employeeEmail}")
    public EmployeeResponseDto getEmployeeByEmail(@PathVariable(value = "employeeEmail") String employeeEmail) {
        return employeeService.getEmployeeByEmail(employeeEmail);
    }
}
