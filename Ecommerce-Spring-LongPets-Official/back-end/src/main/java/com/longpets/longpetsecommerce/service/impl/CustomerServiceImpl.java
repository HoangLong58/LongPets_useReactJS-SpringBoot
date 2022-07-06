package com.longpets.longpetsecommerce.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.model.Role;
import com.longpets.longpetsecommerce.data.model.Ward;
import com.longpets.longpetsecommerce.data.repository.CustomerRepository;
import com.longpets.longpetsecommerce.data.repository.RoleRepository;
import com.longpets.longpetsecommerce.data.repository.WardRepository;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.CustomerResponseDto;
import com.longpets.longpetsecommerce.dto.response.MessageResponseDto;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerEmail(customerEmail);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found in the datebase");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        customer.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(customer.getCustomerEmail(), customer.getCustomerPassword(), authorities);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToCustomer(String customerEmail, String roleName) {
        Customer customer = customerRepository.findByCustomerEmail(customerEmail);
        Role role = roleRepository.findByRoleName(roleName);
        customer.getRoles().add(role);
    }

    @Override
    public Customer getCustomer(String customerEmail) {
        return customerRepository.findByCustomerEmail(customerEmail);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                //TODO: put it to config file
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String customerEmail = decodedJWT.getSubject();
                Customer customer = customerRepository.findByCustomerEmail(customerEmail);
                String access_token = JWT.create()
                        .withSubject(customer.getCustomerEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", customer.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
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
    public Customer registerCustomer(Customer customer) {
        if (customerRepository.findByCustomerEmail(customer.getCustomerEmail()) != null) {
            throw new ApiRequestException("Email is already in use");
        }
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        Role role = roleRepository.findByRoleName("ROLE_CUSTOMER");
        customer.getRoles().add(role);
        return customerRepository.save(customer);
    }

    @Override
    public MessageResponseDto checkCustomerPhone(Long customerId, String customerPhone) {
        MessageResponseDto messageResponseDto = new MessageResponseDto();

        List<CustomerResponseDto> customerResponseDtos = customerRepository.checkCustomerPhone(customerId, customerPhone);
        if(customerResponseDtos.isEmpty()) {
            messageResponseDto.setMessage("Unregistered phone number");
        } else {
            messageResponseDto.setMessage("Registered phone number");
        }
        return messageResponseDto;
    }

    @Override
    public void updateCustomer(UpdateCustomerRequestDto updateCustomerRequestDto) {
        try {
            customerRepository.updateCustomer(updateCustomerRequestDto.getWardId(),
                    updateCustomerRequestDto.getCustomerName(),
                    updateCustomerRequestDto.getCustomerBirthday(),
                    updateCustomerRequestDto.getCustomerGender(),
                    updateCustomerRequestDto.getCustomerPhone(),
                    updateCustomerRequestDto.getCustomerAddress(),
                    updateCustomerRequestDto.getCustomerAvatar(),
                    updateCustomerRequestDto.getCustomerId());
        }
        catch (Exception e) {
            throw new ApiRequestException("Error when update customer");
        }
    }

    @Override
    public CustomerResponseDto findCustomerByCustomerId(Long customerId) {
        return customerRepository.findCustomerByCustomerId(customerId);
    }
}
