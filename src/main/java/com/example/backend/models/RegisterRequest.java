package com.example.backend.models;

import com.example.backend.embedded.Address;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String userName;

    private String email;

    private String password;

    private String role;

    @Embedded
    private Address address;

}
