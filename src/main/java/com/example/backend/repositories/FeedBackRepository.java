package com.example.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entities.FeedBack;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long>{
     
    @Query("SELECT f FROM FeedBack f WHERE f.donation.id = :donationId")
    List<FeedBack> findByDonationId(Long donationId);
}
