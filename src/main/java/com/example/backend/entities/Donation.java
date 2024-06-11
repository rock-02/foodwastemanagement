package com.example.backend.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Donation {

    @Id
    @SequenceGenerator(name = "donation_sequence", sequenceName = "donation_sequence", allocationSize = 1)
    @GeneratedValue(generator = "donation_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String foodDescription;

    private String foodType;

    private Integer quantity;

    @ManyToOne
    private User donor;

    private String donationStatus;

    private Date createdAt;

    @ManyToOne
    private User recipient;

    @JsonIgnore
    @ManyToMany
    List<FeedBack> feedbacks = new ArrayList<>();

    @ManyToOne
    private User deleveryBoy;

    private Date deleveryDateTimeStarted;

    private Date deleveryExpectedDateTime;

}
