package com.example.backend.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {
    @Id
    @SequenceGenerator(name = "request_sequence", sequenceName = "request_sequence", allocationSize = 1)
    @GeneratedValue(generator = "request_sequence")

    private Long id;

    private String ngoName;
    private String contactPerson;
    private String registrationNumber;
    private String email;
    private String phoneNumber;
    private String address;

    private String requestStatus;

    private Date requestDateTime;

    private String requestDescription;

    @ManyToOne
    private User reqUser;

}
