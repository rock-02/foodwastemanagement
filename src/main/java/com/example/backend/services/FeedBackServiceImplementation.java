package com.example.backend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entities.Donation;
import com.example.backend.entities.FeedBack;
import com.example.backend.repositories.FeedBackRepository;

@Service
public class FeedBackServiceImplementation implements FeedBackService {

    @Autowired
    private DonationService donationService;

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public FeedBack saveFeedBack(FeedBack feedBack, Long donationId, Long userId) throws Exception {

        Donation donation = donationService.findDonationById(donationId);

        if (donation == null) {
            throw new Exception("Donation not found");
        }

        if (donation.getRecipient().getId() != userId) {
            throw new Exception("You are not the recipient of this donation");
        }

        FeedBack newFeedBack = new FeedBack();

        newFeedBack.setDonation(donation);

        newFeedBack.setRating(feedBack.getRating());

        newFeedBack.setFeedback(feedBack.getFeedback());

        newFeedBack.setTimeStamp(new Date(System.currentTimeMillis()));

        FeedBack saveFeedBack = feedBackRepository.save(newFeedBack);

        donation.getFeedbacks().add(saveFeedBack);

        return saveFeedBack;

    }

    @Override
    public FeedBack findFeedBackById(Long id) throws Exception {

        FeedBack feedBack = feedBackRepository.findById(id).orElse(null);

        if (feedBack == null) {
            throw new Exception("FeedBack not found");
        }

        return feedBack;
    }

    @Override
    public List<FeedBack> findFeedBackByDonation(Long donationId) throws Exception {

        Donation donation = donationService.findDonationById(donationId);

        if (donation == null) {
            throw new Exception("Donation not found");
        }

        return null;
    }

}
