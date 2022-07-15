package com.longpets.longpetsecommerce.controller;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.dto.request.AddRoleToCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.request.RegisterRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import static java.util.Arrays.stream;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"})
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

//    @GetMapping("/users")
//    public ResponseEntity<List<Customer>>getCustomers() {
//        return ResponseEntity.ok().body(customerService.getCustomers());
//    }

//    @PostMapping("/user/save")
//    public ResponseEntity<Customer>saveCustomer(@RequestBody Customer customer) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer/user/save").toUriString());
//        return ResponseEntity.created(uri).body(customerService.saveCustomer(customer));
//    }
//
//    @PostMapping("/role/save")
//    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customer/role/save").toUriString());
//        return ResponseEntity.created(uri).body(customerService.saveRole(role));
//    }
//
//    @PostMapping("/role/add-to-user")
//    public ResponseEntity<?>addRoleToUser(@Valid @RequestBody AddRoleToCustomerRequestDto roleToUserForm) {
//        customerService.addRoleToCustomer(roleToUserForm.getCustomerEmail(), roleToUserForm.getRoleName());
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?>registerCustomer(@Valid @RequestBody RegisterRequestDto customer) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
//        return ResponseEntity.created(uri).body(customerService.registerCustomer(customer));
//    }
//
//    @GetMapping("/token/refresh")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        customerService.refreshToken(request, response);
//    }

//    @GetMapping("/check-customer-phone/{customerId}-{customerPhone}")
//    public MessageResponseDto checkCustomerPhone(@PathVariable(value = "customerId") Long customerId, @PathVariable(value = "customerPhone") String customerPhone) {
//        return customerService.checkCustomerPhone(customerId, customerPhone);
//    };

//    @PutMapping("/update-customer")
//    public void updateCustomer(@Valid @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto) {
//        customerService.updateCustomer(updateCustomerRequestDto);
//    }

//    @GetMapping("/find-customer/{customerId}")
//    public CustomerResponseDto findCustomerByCustomerId(@PathVariable(value = "customerId") Long customerId) {
//       return customerService.findCustomerByCustomerIdd(customerId);
//    }

//    @GetMapping("/find-customer-by-emailCustomer/{customerEmail}")
//    public CustomerItfResponseDto findCustomerByCustomerEmail(@PathVariable(value = "customerEmail") String customerEmail) {
//       return customerService.findCustomerByCustomerEmail(customerEmail);
//    }

//    @GetMapping("/get-customer")
//    public List<CustomerItfResponseDto> getCustomer() {
//        return customerService.getCustomer();
//    }

//    @GetMapping("/get-customer-by-customer-name/{customerName}")
//    public List<CustomerItfResponseDto> getCustomerByCustomerName(@PathVariable(value = "customerName") String customerName) {
//        return customerService.getCustomerByCustomerName(customerName);
//    }

//    @GetMapping("/get-customer-quantity")
//    public CustomerQuantityResponseDto getCustomerQuantity() {
//        return customerService.getCustomerQuantity();
//    }

    @PutMapping("/delete-customer/{customerId}")
    public void deleteCustomer(@PathVariable(value = "customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/get-ward-district-city/{wardId}")
    public WardDistrictCityResponseDto getWardDistrictCity(@PathVariable(value = "wardId") String wardId) {
        return customerService.getWardDistrictCity(wardId);
    }
//    =========================================== FIX ===============================================
    @GetMapping("/users")
    public List<CustomerResponseDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }
    @GetMapping("/find-customer/{customerId}")
    public CustomerResponseDto getCustomerByCustomerId(@PathVariable(value = "customerId") @NotNull @Min(0) Long customerId) {
        return customerService.getCustomerByCustomerId(customerId);
    }

    @GetMapping("/get-customer-by-customer-name/{customerName}")
    public List<CustomerResponseDto> getCustomerByCustomerName(@PathVariable(value = "customerName") @NotNull @Min(0) String customerName) {
        return customerService.getAllCustomerByCustomerName(customerName);
    }

    @GetMapping("/get-customer-quantity")
    public CustomerCountResponseDto getCustomerCount() {
        return customerService.getCustomerCount();
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

    @GetMapping("/find-customer-by-emailCustomer/{customerEmail}")
    public CustomerResponseDto findCustomerByCustomerEmail(@PathVariable(value = "customerEmail") @NotNull String customerEmail) {
       return customerService.findCustomerByCustomerEmail(customerEmail);
    }

    @GetMapping("/check-customer-phone/{customerPhone}-{customerId}")
    public MessageResponseDto checkCustomerPhone(@PathVariable(value = "customerPhone") @NotNull String customerPhone, @PathVariable(value = "customerId") @NotNull @Min(0) Long customerId) {
        return customerService.checkCustomerPhone(customerPhone, customerId);
    };

    @PutMapping("/update-customer")
    public void updateCustomer(@Valid @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto) {
        customerService.updateCustomer(updateCustomerRequestDto);
    }
}
