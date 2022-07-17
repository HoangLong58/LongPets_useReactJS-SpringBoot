package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.dto.request.RegisterRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CustomerService {

    WardDistrictCityResponseDto getWardDistrictCity(String wardId);

//    =========================================== FIX ===============================================
    List<CustomerResponseDto> getAllCustomer();

    CustomerResponseDto getCustomerByCustomerId(Long customerId);

    List<CustomerResponseDto> getAllCustomerByCustomerName(String customerName);

    CustomerCountResponseDto getCustomerCount();

    Customer saveCustomer(Customer customer);

    Role saveRole(Role role);

    void addRoleToCustomer(String emailCustomer, String roleName);

    Customer registerCustomer(RegisterRequestDto customer);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    CustomerResponseDto findCustomerByCustomerEmail(String customerEmail);

    MessageResponseDto checkCustomerPhone(String customerPhone, Long customerId);

    CustomerResponseDto updateCustomer(UpdateCustomerRequestDto updateCustomerRequestDto);

    void deleteCustomer(Long customerId);

}
