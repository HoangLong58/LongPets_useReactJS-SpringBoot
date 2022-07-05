package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.dto.response.MessageResponseDto;

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

    Customer registerCustomer(Customer customer);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    MessageResponseDto checkCustomerPhone(Long customerId, String customerPhone);
}
