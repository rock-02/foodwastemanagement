package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Donation;
import com.example.backend.entities.User;
import com.example.backend.services.DonationService;
import com.example.backend.services.UserService;

@RestController
@RequestMapping("api/recipient")
public class RequestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DonationService donationService;

    @GetMapping("/request")
    public User requestDonation(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        return donationService.requestDonation(user);
    }

    @GetMapping("/requests")
    public java.util.List<User> findUsersByRequestDonation(@RequestHeader("Authorization") String token)
            throws Exception {

        User user = userService.findByjwt(token);

        java.util.List<User> request = donationService.findRequestDonations();

        return request;
    }

    @GetMapping("/requests/user")
    public List<Donation> findUserPastDonations(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        return donationService.findPastRequestDonations(user);
    }


}
