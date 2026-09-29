package com.stschcool.customerservice.service;

import com.stschcool.customerservice.dto.request.CustomerDto;
import com.stschcool.customerservice.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(CustomerDto customer);

    Customer getById(int id);

    List<Customer> getAll();

    Customer update(int id, Customer customer);

    void deleteById(int id);
}
