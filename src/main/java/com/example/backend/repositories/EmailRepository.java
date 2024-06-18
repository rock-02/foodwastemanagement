package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.EmailDetails;

@Repository
public interface EmailRepository extends JpaRepository<EmailDetails, Long> {

}
