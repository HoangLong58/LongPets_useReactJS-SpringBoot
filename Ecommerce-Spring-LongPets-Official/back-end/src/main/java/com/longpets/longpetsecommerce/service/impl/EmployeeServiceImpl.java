package com.longpets.longpetsecommerce.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Employee;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.data.repository.CustomerRepository;
import com.longpets.longpetsecommerce.data.repository.EmployeeRepository;
import com.longpets.longpetsecommerce.data.repository.RoleRepository;
import com.longpets.longpetsecommerce.dto.request.AdminRegisterRequestDto;
import com.longpets.longpetsecommerce.dto.request.RegisterRequestDto;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Transactional
@Qualifier("EmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String employeeEmail) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmployeeEmail(employeeEmail);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found in the datebase");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        employee.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(employee.getEmployeeEmail(), employee.getEmployeePassword(), authorities);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setEmployeePassword(passwordEncoder.encode(employee.getEmployeePassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToEmployee(String employeeEmail, String roleName) {
        Employee employee = employeeRepository.findByEmployeeEmail(employeeEmail);
        Role role = roleRepository.findByRoleName(roleName);
        employee.getRoles().add(role);
    }

    @Override
    public Employee getEmployee(String employeeEmail) {
        return employeeRepository.findByEmployeeEmail(employeeEmail);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshTokenAdmin = authorizationHeader.substring("Bearer ".length());
                //TODO: put it to config file
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshTokenAdmin);
                String employeeEmail = decodedJWT.getSubject();
                Employee employee = employeeRepository.findByEmployeeEmail(employeeEmail);
                String accessTokenAdmin = JWT.create()
                        .withSubject(employee.getEmployeeEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", employee.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessTokenAdmin", accessTokenAdmin);
                tokens.put("refreshTokenAdmin", refreshTokenAdmin);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new ApiRequestException("Refresh token is missing");
        }
    }

    //    Register new customer
    @Override
    public Employee registerEmployee(AdminRegisterRequestDto adminRegisterRequestDto) {
        Employee employee = modelMapper.map(adminRegisterRequestDto, Employee.class);
        System.out.printf("Employee:" + employee.toString());
        if (employeeRepository.findByEmployeeEmail(employee.getEmployeeEmail()) != null) {
            throw new ApiRequestException("Email employee is already in use");
        }
        employee.setEmployeePassword(passwordEncoder.encode(employee.getEmployeePassword()));
        Role role = roleRepository.findByRoleName("ROLE_ADMIN");
        employee.getRoles().add(role);
        return employeeRepository.save(employee);
    }
}
