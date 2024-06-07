package com.example.backend.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String area;
    private String city;
    private String district;
    private String state;

    private String pinCode;

}
