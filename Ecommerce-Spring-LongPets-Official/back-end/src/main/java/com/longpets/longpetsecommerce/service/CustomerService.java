package com.longpets.longpetsecommerce.service;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Role saveRole(Role role);
    void addRoleToCustomer(String emailCustomer, String roleName);
    Customer getCustomer(String emailCustomer);
    List<Customer> Customers();
}
