package com.example.backend.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.backend.embedded.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id

    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne
    private Role role;

    private String profilePicture;

    @Embedded
    private Address address;

    @ManyToMany
    @JsonIgnore
    List<Donation> donations = new ArrayList<>();

    private boolean requestDonation = false;

    private Date requestDateTime;
}
