package com.example.backend.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedBack {

    @Id
    @SequenceGenerator(name = "feedback_sequence", sequenceName = "feedback_sequence", allocationSize = 1)
    @GeneratedValue(generator = "feedback_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String feedback;

    private float rating;

    @ManyToOne
    private Donation donation;

    private Date timeStamp;
}
