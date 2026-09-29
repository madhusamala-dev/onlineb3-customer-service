package com.stschcool.customerservice.dto.request;

import com.stschcool.customerservice.enums.Gender;
import lombok.Data;

@Data
public class CustomerDto {
    private String name;
    private String email;
    private String password;
    private Gender gender;
}
