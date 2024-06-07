package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;
import com.example.backend.services.DonationService;
import com.example.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/donor")
public class DonorConroller {

    @Autowired
    private UserService userService;

    @Autowired
    private DonationService donationService;

    @PostMapping("/create")
    public Donation createDonation(@RequestBody Donation donation, @RequestHeader("Authorization") String token)
            throws Exception {

        User user = userService.findByjwt(token);

        Donation savedDonation = donationService.saveDonation(donation, user);

        return savedDonation;
    }

    @GetMapping("/donations")
    public Object getDonations(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        return donationService.findByDonor(user);
    }

}

