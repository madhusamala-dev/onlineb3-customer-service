package com.stschcool.customerservice.repository;

import com.stschcool.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    //query methods (findByProperties)

    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
