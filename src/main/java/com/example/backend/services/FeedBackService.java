package com.example.backend.services;


import com.example.backend.entities.FeedBack;

public interface FeedBackService {

    public FeedBack saveFeedBack(FeedBack feedBack,Long DonationId,Long userId) throws Exception;

    public FeedBack findFeedBackById(Long id) throws Exception;

    public java.util.List<FeedBack> findFeedBackByDonation(Long donationId) throws Exception;

}
