package com.example.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT d FROM Donation d WHERE d.donor = :user")
    java.util.List<Donation> findByDonor(User user);

    @Query("SELECT d From Donation d where d.recipient = :user")
    List<Donation> findByPastRequestDonations(User user);


    @Query("SELECT d FROM Donation d WHERE d.deleveryBoy = :user")
    List<Donation> findByDeleveryBoy(User user);
}

