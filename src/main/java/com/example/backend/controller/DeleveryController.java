package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;
import com.example.backend.services.DonationService;
import com.example.backend.services.UserService;

import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/delivery")
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

    @GetMapping("/donation/{id}")

    public Donation getDonation(@PathVariable("id") Long donationId,
            @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("DELEVERY_AGENT")) {
            throw new Exception("You are not authenticated to access this route");
        }

        Donation donation = donationService.getDonation(donationId, user);

        return donation;
    }

    @GetMapping("/donations")

    public List<Donation> findDonationByDeleveryBoy(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("DELEVERY_AGENT")) {
            throw new Exception("You are not authenticated to access this route");
        }

        List<Donation> donations = donationService.findDonationByDeleveryBoy(user);

        return donations;
    }

}
