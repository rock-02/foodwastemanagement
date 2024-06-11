package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;
import com.example.backend.services.DonationService;
import com.example.backend.services.FeedBackService;
import com.example.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/create")
    public Donation createDonation(@RequestBody Donation donation, @RequestHeader("Authorization") String token)
            throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("DONOR")) {
            throw new Exception("You are not authorized to access this route");
        }

        Donation savedDonation = donationService.saveDonation(donation, user);

        return savedDonation;
    }

    @GetMapping("/donations")
    public Object getDonations(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("DONOR")) {
            throw new Exception("You are not authorized to access this route");
        }

        return donationService.findByDonor(user);
    }

    @GetMapping("/feedbacks/{donationId}")
    public Object getFeedbacks(@RequestHeader("Authorization") String token,
            @PathVariable("donationId") Long donationId) throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("DONOR")) {
            throw new Exception("You are not authorized to access this route");
        }

        return feedBackService.findFeedBackByDonation(donationId);

        
    }

}
