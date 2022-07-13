package com.longpets.longpetsecommerce.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.dto.request.AddRoleToCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.request.RegisterRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.CustomerQuantityResponseDto;
import com.longpets.longpetsecommerce.dto.response.CustomerResponseDto;
import com.longpets.longpetsecommerce.dto.response.MessageResponseDto;
import com.longpets.longpetsecommerce.dto.response.WardDistrictCityResponseDto;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.service.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/users")
    public ResponseEntity<List<Customer>>getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<Customer>saveCustomer(@RequestBody Customer customer) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer/user/save").toUriString());
        return ResponseEntity.created(uri).body(customerService.saveCustomer(customer));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer/role/save").toUriString());
        return ResponseEntity.created(uri).body(customerService.saveRole(role));
    }

    @PostMapping("/role/add-to-user")
    public ResponseEntity<?>addRoleToUser(@Valid @RequestBody AddRoleToCustomerRequestDto roleToUserForm) {
        customerService.addRoleToCustomer(roleToUserForm.getCustomerEmail(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?>registerCustomer(@Valid @RequestBody RegisterRequestDto customer) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(customerService.registerCustomer(customer));
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        customerService.refreshToken(request, response);
    }

    @GetMapping("/check-customer-phone/{customerId}-{customerPhone}")
    public MessageResponseDto checkCustomerPhone(@PathVariable(value = "customerId") Long customerId, @PathVariable(value = "customerPhone") String customerPhone) {
        return customerService.checkCustomerPhone(customerId, customerPhone);
    };

    @PutMapping("/update-customer")
    public void updateCustomer(@Valid @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto) {
        customerService.updateCustomer(updateCustomerRequestDto);
    }

    @GetMapping("/find-customer/{customerId}")
    public CustomerResponseDto findCustomerByCustomerId(@PathVariable(value = "customerId") Long customerId) {
       return customerService.findCustomerByCustomerId(customerId);
    }

    @GetMapping("/find-customer-by-emailCustomer/{customerEmail}")
    public CustomerResponseDto findCustomerByCustomerEmail(@PathVariable(value = "customerEmail") String customerEmail) {
       return customerService.findCustomerByCustomerEmail(customerEmail);
    }

    @GetMapping("/get-customer")
    public List<CustomerResponseDto> getCustomer() {
        return customerService.getCustomer();
    }

    @GetMapping("/get-customer-by-customer-name/{customerName}")
    public List<CustomerResponseDto> getCustomerByCustomerName(@PathVariable(value = "customerName") String customerName) {
        return customerService.getCustomerByCustomerName(customerName);
    }

    @GetMapping("/get-customer-quantity")
    public CustomerQuantityResponseDto getCustomerQuantity() {
        return customerService.getCustomerQuantity();
    }

    @PutMapping("/delete-customer/{customerId}")
    public void deleteCustomer(@PathVariable(value = "customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/get-ward-district-city/{wardId}")
    public WardDistrictCityResponseDto getWardDistrictCity(@PathVariable(value = "wardId") String wardId) {
        return customerService.getWardDistrictCity(wardId);
    }
}

//@Data
//class RoleToUserForm {
//    private String emailCustomer;
//    private String roleName;
//}