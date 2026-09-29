package com.stschcool.customerservice.exception;

public class CustomerExistsException extends RuntimeException {

    public CustomerExistsException(String message) {
        super(message);
    }
}
