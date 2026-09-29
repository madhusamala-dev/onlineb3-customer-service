package com.stschcool.customerservice.service;

import com.stschcool.customerservice.dto.request.CustomerDto;
import com.stschcool.customerservice.entity.Customer;
import com.stschcool.customerservice.enums.Status;
import com.stschcool.customerservice.exception.CustomerExistsException;
import com.stschcool.customerservice.exception.CustomerNotFoundException;
import com.stschcool.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer save(CustomerDto customerDto) {

        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new CustomerExistsException(
                    "Customer already exists with email: " + customerDto.getEmail()
            );
        }

        //convert CustomerDto to Customer Entity
        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .gender(customerDto.getGender())
                .build();
        customer.setStatus(Status.ACTIVE);
        customer.setCreatedAt(LocalDateTime.now());

        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(int id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id: " + id
                        )
                );
    }

    @Override
    public List<Customer> getAll() {

        return customerRepository.findAll();
    }

    @Override
    public Customer update(int id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id: " + id
                        )
                );

        // Check if email is being changed
        if (!existingCustomer.getEmail().equals(customer.getEmail())
                && customerRepository.existsByEmail(customer.getEmail())) {

            throw new CustomerExistsException(
                    "Customer already exists with email: " + customer.getEmail()
            );
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPassword(customer.getPassword());
        existingCustomer.setStatus(customer.getStatus());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteById(int id) {

        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(
                    "Customer not found with id: " + id
            );
        }

        customerRepository.deleteById(id);
    }
}
