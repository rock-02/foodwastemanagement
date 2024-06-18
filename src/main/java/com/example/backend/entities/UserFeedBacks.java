package com.example.backend.entities;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFeedBacks {
    @Id
    @SequenceGenerator(name = "user_feedback_sequence", sequenceName = "user_feedback_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_feedback_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String feedback;
    private float rating;

    @ManyToOne
    private User user;
}
