package com.stschcool.customerservice.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse<T> {

    private int statusCode; //404,409,200,201
    private String message;
    private T data;
}
