package com.example.backend.services;

import java.util.List;

import com.example.backend.entities.Donation;
import com.example.backend.entities.Request;
import com.example.backend.entities.User;

public interface DonationService {

    public Donation saveDonation(Donation donation, User user) throws Exception;

    public List<Donation> findByDonor(User user) throws Exception;

    public User requestDonation(User user, Request request) throws Exception;

    public List<User> findRequestDonations();

    public List<Donation> findPastRequestDonations(User user);

    public Donation findDonationById(Long id) throws Exception;

    public Donation updateDonationStatus(Long donationId, String status, User user) throws Exception;

    public Donation getDonation(Long donationId, User user) throws Exception;

    public List<Donation> findDonationByDeleveryBoy(User user);
}
