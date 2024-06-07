package com.example.backend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;
import com.example.backend.repositories.DonationRepository;
import com.example.backend.repositories.UserRepository;

@Service
public class DonationServiceImplementation implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Donation saveDonation(Donation donation, User user) throws Exception {

        Donation newDonation = new Donation();

        newDonation.setFoodDescription(donation.getFoodDescription());

        newDonation.setFoodType(donation.getFoodType());

        newDonation.setQuantity(donation.getQuantity());

        newDonation.setDonationStatus("Pending");

        newDonation.setDonor(user);

        newDonation.setCreatedAt(new Date(System.currentTimeMillis()));

        Donation saveDonation = donationRepository.save(newDonation);

        user.getDonations().add(saveDonation);

        userRepository.save(user);
        return saveDonation;

    }

    @Override
    public List<Donation> findByDonor(User user) throws Exception {
        return donationRepository.findByDonor(user);
    }

    @Override
    public User requestDonation(User user) {

        user.setRequestDonation(true);

        user.setRequestDateTime(new Date(System.currentTimeMillis()));

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public List<User> findRequestDonations() {
        return userRepository.findUsersByRequestDonation();
    }

    @Override
    public List<Donation> findPastRequestDonations(User user) {

        List<Donation> requests = donationRepository.findByPastRequestDonations(user);

        return requests;
    }

}
