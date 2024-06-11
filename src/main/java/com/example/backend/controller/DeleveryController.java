package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;
import com.example.backend.services.DonationService;
import com.example.backend.services.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/delevery")
public class DeleveryController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @PutMapping("/donation/{id}/{status}")
    public Donation updateDonationStatus(@PathVariable String status,
            @PathVariable("id") Long donationId,
            @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("DELEVERY_AGENT")) {
            throw new Exception("You are not authenticated to access this route");
        }

        Donation donation = donationService.updateDonationStatus(donationId, status, user);

        return donation;
    }

}
