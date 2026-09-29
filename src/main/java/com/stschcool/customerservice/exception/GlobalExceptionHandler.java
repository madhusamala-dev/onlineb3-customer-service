package com.stschcool.customerservice.exception;

import com.stschcool.customerservice.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = CustomerNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomerNotFoundException(CustomerNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<String>builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .data(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(exception = CustomerExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomerExistsException(CustomerExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponse.<String>builder()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .data(ex.getMessage())
                        .build());   }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.<String>builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .data(ex.getMessage())
                        .build()); }
}
