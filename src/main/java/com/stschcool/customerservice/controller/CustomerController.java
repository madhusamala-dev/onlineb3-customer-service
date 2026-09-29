package com.stschcool.customerservice.controller;

import com.stschcool.customerservice.dto.request.CustomerDto;
import com.stschcool.customerservice.dto.response.ApiResponse;
import com.stschcool.customerservice.entity.Customer;
import com.stschcool.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> createCustomer(
            @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Customer>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("customer created successfully")
                        .data(customerService.save(customerDto))
                        .build());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(
            @PathVariable int id) {

        return ResponseEntity.ok(ApiResponse.<Customer>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("customer fetched successfully")
                        .data(customerService.getById(id))
                        .build());
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        return ResponseEntity.ok(
                customerService.getAll()
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable int id,
            @RequestBody Customer customer) {

        return ResponseEntity.ok(
                customerService.update(id, customer)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable int id) {
        customerService.deleteById(id);
        //return ResponseEntity.ok("customer deleted successfully");
        return ResponseEntity.noContent().build();
    }
}
