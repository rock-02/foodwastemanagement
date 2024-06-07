package com.example.backend.services;

import java.util.List;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;

public interface DonationService {

    public Donation saveDonation(Donation donation, User user) throws Exception;

    public List<Donation> findByDonor(User user) throws Exception;

    public User requestDonation(User user);

    public List<User> findRequestDonations();

    public List<Donation> findPastRequestDonations(User user);

}
