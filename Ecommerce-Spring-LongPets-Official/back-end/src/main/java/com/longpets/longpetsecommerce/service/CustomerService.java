package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.dto.request.RegisterRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.CustomerQuantityResponseDto;
import com.longpets.longpetsecommerce.dto.response.CustomerResponseDto;
import com.longpets.longpetsecommerce.dto.response.MessageResponseDto;
import com.longpets.longpetsecommerce.dto.response.WardDistrictCityResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Role saveRole(Role role);
    void addRoleToCustomer(String emailCustomer, String roleName);
    Customer getCustomer(String emailCustomer);
    List<Customer> getCustomers();

    Customer registerCustomer(RegisterRequestDto customer);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    MessageResponseDto checkCustomerPhone(Long customerId, String customerPhone);

    void updateCustomer(UpdateCustomerRequestDto updateCustomerRequestDto);

    CustomerResponseDto findCustomerByCustomerId(Long customerId);

    CustomerResponseDto findCustomerByCustomerEmail(String customerEmail);

    List<CustomerResponseDto> getCustomer();
    List<CustomerResponseDto> getCustomerByCustomerName(String customerName);

    CustomerQuantityResponseDto getCustomerQuantity();

    void deleteCustomer(Long customerId);

    WardDistrictCityResponseDto getWardDistrictCity(String wardId);
}
