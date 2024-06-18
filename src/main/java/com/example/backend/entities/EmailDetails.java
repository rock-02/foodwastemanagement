package com.example.backend.entities;

import javax.sound.midi.Sequence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmailDetails {
    @Id
    @SequenceGenerator(name = "email_sequence", sequenceName = "email_sequence", allocationSize = 1)
    @GeneratedValue(generator = "email_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
