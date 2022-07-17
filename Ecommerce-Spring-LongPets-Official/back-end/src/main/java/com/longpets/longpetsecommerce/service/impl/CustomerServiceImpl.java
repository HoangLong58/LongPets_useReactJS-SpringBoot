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
import com.longpets.longpetsecommerce.dto.request.RegisterRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdateCustomerRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.exception.ResourceNotFoundException;
import com.longpets.longpetsecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final WardRepository wardRepository;
    private final ModelMapper modelMapper;


    @Override
    public WardDistrictCityResponseDto getWardDistrictCity(String wardId) {
        return customerRepository.getWardDistrictCity(wardId);
    }
//    =========================================== FIX ===============================================
    @Override
    public List<CustomerResponseDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAllByCustomerIdNot(0L);
        List<CustomerResponseDto> customerResponseDtos = modelMapper.map(customers,
                new TypeToken<List<CustomerResponseDto>>() {
                }.getType());

        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto getCustomerByCustomerId(Long customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find customer with id "+ customerId));
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomerByCustomerName(String customerName) {
        List<Customer> customers = customerRepository.findCustomerByCustomerNameContaining(customerName);
        List<CustomerResponseDto> customerResponseDtos = modelMapper.map(customers,
                new TypeToken<List<CustomerResponseDto>>() {
                }.getType());

        return customerResponseDtos;
    }

    @Override
    public CustomerCountResponseDto getCustomerCount() {
        Long customerCount = customerRepository.count();
        CustomerCountResponseDto customerCountResponseDto = new CustomerCountResponseDto();
        customerCountResponseDto.setCustomerQuantity(customerCount - 1);
        return customerCountResponseDto;
    }

    @Override
    public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByCustomerEmail(customerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found in the datebase"));
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
        Customer customer = customerRepository.findCustomerByCustomerEmail(customerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find customer by email "+customerEmail));
        Role role = roleRepository.findByRoleName(roleName);
        customer.getRoles().add(role);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                //TODO: put it to config file
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String customerEmail = decodedJWT.getSubject();
                Customer customer = customerRepository.findCustomerByCustomerEmail(customerEmail)
                        .orElseThrow(() -> new ResourceNotFoundException("Can't find customer by email "+customerEmail));
                String accessToken = JWT.create()
                        .withSubject(customer.getCustomerEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", customer.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refresh_token", refreshToken);
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
    public Customer registerCustomer(RegisterRequestDto registerRequestDto) {
        Customer customer = modelMapper.map(registerRequestDto, Customer.class);
        System.out.printf("Customer:" + customer.toString());
        if (customerRepository.findCustomerByCustomerEmail(customer.getCustomerEmail())
                .isPresent()) {
            throw new ApiRequestException("Email is already in use");
        }
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        Role role = roleRepository.findByRoleName("ROLE_CUSTOMER");
        customer.getRoles().add(role);
        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponseDto findCustomerByCustomerEmail(String customerEmail) {
        Customer customer = customerRepository.findCustomerByCustomerEmail(customerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find customer with email "+ customerEmail));
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public MessageResponseDto checkCustomerPhone(String customerPhone, Long customerId) {
        MessageResponseDto messageResponseDto = new MessageResponseDto();
        List<Customer> customers = customerRepository.findCustomersByCustomerPhone(customerPhone);
        if(customers.isEmpty()) {
            messageResponseDto.setMessage("Unregistered phone number");
        } else {
            Long isFindCustomerId = customers.get(0).getCustomerId();
            if(isFindCustomerId == customerId) {
                messageResponseDto.setMessage("Unregistered phone number");
            } else {
                messageResponseDto.setMessage("Registered phone number");
            }
        }
        return messageResponseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(UpdateCustomerRequestDto updateCustomerRequestDto) {
//        Get customerId and wardId param
        Long customerId = updateCustomerRequestDto.getCustomerId();
        String wardId = updateCustomerRequestDto.getWardId();
//        Find customer
        Customer customer = customerRepository.findCustomerByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find customer by customer id " + customerId));
        String customerEmailFind = customer.getCustomerEmail();
        String customerPasswordFind = customer.getCustomerPassword();
//        Find ward
        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find ward by ward id " + wardId));
        customer.setWardCustomer(ward);
//        map with updateCustomerRequestDto
        modelMapper.map(updateCustomerRequestDto, customer);
//        set email, password and save database
        customer.setCustomerEmail(customerEmailFind);
        customer.setCustomerPassword(customerPasswordFind);
        customer = customerRepository.save(customer);
        System.out.println("CUSTOMER:"+customer.toString());
//        map return customerResponseDto
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        return customerResponseDto;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
