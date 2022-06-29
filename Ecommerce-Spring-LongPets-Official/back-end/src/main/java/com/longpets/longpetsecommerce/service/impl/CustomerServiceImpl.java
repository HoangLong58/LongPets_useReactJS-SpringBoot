package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.data.repository.CustomerRepository;
import com.longpets.longpetsecommerce.data.repository.RoleRepository;
import com.longpets.longpetsecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToCustomer(String emailCustomer, String roleName) {
        Customer customer = customerRepository.findByEmailCustomer(emailCustomer);
        Role role = roleRepository.findByRoleName(roleName);
        customer.setRoleCustomer(role);
    }

    @Override
    public Customer getCustomer(String emailCustomer) {
        return null;
    }

    @Override
    public List<Customer> Customers() {
        return null;
    }
}
