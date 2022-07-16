package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Customer;
import com.longpets.longpetsecommerce.data.repository.CustomerRepository;
import com.longpets.longpetsecommerce.dto.response.CustomerResponseDto;
import com.longpets.longpetsecommerce.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {
    @Test
    public void getCustomerByCustomerId_WhenCustomerIdValid_ReturnCustomerResponseDto(){
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        Customer customer = mock(Customer.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        CustomerServiceImpl customerService = mock(CustomerServiceImpl.class);

        when(customerRepository.findCustomerByCustomerId(1L)).thenReturn(Optional.of(customer));
        CustomerResponseDto customerResponseDtoExpect = modelMapper.map(customer, CustomerResponseDto.class);
        CustomerResponseDto customerResponseDtoActual = customerService.getCustomerByCustomerId(1L);

        assertThat(customerResponseDtoActual).isEqualTo(customerResponseDtoExpect);
    }

    @Test
    public void getCustomerByCustomerId_WhenCustomerIdInValid_ReturnResourceNotFoundException(){
        CustomerServiceImpl customerService = mock(CustomerServiceImpl.class);
        Long customerId = 1L;

        when(customerService.getCustomerByCustomerId(customerId)).thenThrow(new ResourceNotFoundException("Can't find customer with id " + customerId));
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> customerService.getCustomerByCustomerId(customerId));

        assertThat(exception.getMessage()).isEqualTo("Can't find customer with id 1");
    }
}
