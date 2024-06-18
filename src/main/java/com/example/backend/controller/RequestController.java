package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Donation;
import com.example.backend.entities.FeedBack;
import com.example.backend.entities.Request;
import com.example.backend.entities.User;
import com.example.backend.services.DonationService;
import com.example.backend.services.FeedBackService;
import com.example.backend.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/recipient")
public class RequestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private FeedBackService feedBackService;

    @PutMapping("/request")
    public User requestDonation(@RequestHeader("Authorization") String token, @RequestBody Request request)
            throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("RECIPIENT")) {
            throw new Exception("You cannot request donations");

        }

        return donationService.requestDonation(user,request);
    }

    @GetMapping("/requests")
    public java.util.List<User> findUsersByRequestDonation(@RequestHeader("Authorization") String token)
            throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("RECIPIENT")) {
            throw new Exception("You are not authorized to access this route");
        }

        java.util.List<User> request = donationService.findRequestDonations();

        return request;
    }

    @GetMapping("/requests/user")
    public List<Donation> findUserPastDonations(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("User -> " + user.getRole().getName());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        if (!user.getRole().getName().equals("RECIPIENT")) {
            throw new Exception("You are not authorized to access this route");
        }

        return donationService.findPastRequestDonations(user);
    }

    @PostMapping("/feedback/{donationId}")
    public FeedBack saveFeedBack(@RequestBody FeedBack feedBack, @PathVariable("donationId") Long donationId,
            @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        if (!user.getRole().getName().equals("RECIPIENT")) {
            throw new Exception("You cannot give feedback");
        }

        return feedBackService.saveFeedBack(feedBack, donationId, user.getId());
    }

    @GetMapping("/feedback/{id}")

    public FeedBack findFeedBackById(@PathVariable("id") Long id) throws Exception {

        return feedBackService.findFeedBackById(id);
    }

    @GetMapping("/feedback/donation/{donationId}")
    public List<FeedBack> findFeedBackByDonation(@PathVariable("donationId") Long donationId) throws Exception {
        return feedBackService.findFeedBackByDonation(donationId);
    }

}
