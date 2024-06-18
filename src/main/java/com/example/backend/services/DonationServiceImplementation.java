package com.example.backend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entities.Donation;
import com.example.backend.entities.EmailDetails;
import com.example.backend.entities.Request;
import com.example.backend.entities.User;
import com.example.backend.repositories.DonationRepository;
import com.example.backend.repositories.RequestRepository;
import com.example.backend.repositories.UserRepository;

@Service
public class DonationServiceImplementation implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Donation saveDonation(Donation donation, User user) throws Exception {

        Donation newDonation = new Donation();

        newDonation.setFoodDescription(donation.getFoodDescription());

        newDonation.setFoodType(donation.getFoodType());

        newDonation.setQuantity(donation.getQuantity());

        newDonation.setDonationStatus("Pending");

        newDonation.setDonor(user);

        newDonation.setCreatedAt(new Date(System.currentTimeMillis()));

        List<User> requesrtedUsers = userRepository.findUserBasedOnRequestTimeAndRequestDonation();

        List<User> deleveryBoys = userRepository.findUserByDeleveryBoyAndRole();

        if (requesrtedUsers.size() > 0 && deleveryBoys.size() > 0) {
            newDonation.setRecipient(requesrtedUsers.get(0));

            requesrtedUsers.get(0).setRequestDonation(false);

            requesrtedUsers.get(0).setRequestDateTime(null);

            userRepository.save(requesrtedUsers.get(0));

            newDonation.setDeleveryBoy(deleveryBoys.get(0));

            newDonation.setDonationStatus("On the Way");

            newDonation.setDeleveryDateTimeStarted(new Date(System.currentTimeMillis()));

            newDonation.setDeleveryExpectedDateTime(new Date(System.currentTimeMillis() + 3 * 3600000));

            deleveryBoys.get(0).setAvailableToDelevery(false);

            Donation saveDonation = donationRepository.save(newDonation);

            user.getDonations().add(saveDonation);

            return saveDonation;
        } else {
            throw new Exception("No Request Found for Donation");
        }

    }

    @Override
    public List<Donation> findByDonor(User user) throws Exception {
        return donationRepository.findByDonor(user);
    }

    @Override
    public User requestDonation(User user, Request request) throws Exception {

        if (user.isRequestDonation()) {
            user.setRequestDonation(false);
            user.setRequestDateTime(null);
        } else {
            Request newRequest = new Request();
            newRequest.setNgoName(request.getNgoName());
            newRequest.setAddress(request.getAddress());
            newRequest.setContactPerson(request.getContactPerson());
            newRequest.setPhoneNumber(request.getPhoneNumber());
            newRequest.setEmail(request.getEmail());
            newRequest.setRegistrationNumber(request.getRegistrationNumber());
            newRequest.setRequestDescription(request.getRequestDescription());
            newRequest.setRequestStatus(
                    "Pending");
            newRequest.setRequestDateTime(new Date(System.currentTimeMillis()));

            requestRepository.save(newRequest);
            user.setRequestDonation(true);
            user.setRequestDateTime(new Date(System.currentTimeMillis()));
        }

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

    @Override
    public Donation findDonationById(Long id) throws Exception {
        return donationRepository.findById(id).orElseThrow(() -> new Exception("Donation Not Found"));
    }

    @Override
    public Donation updateDonationStatus(Long donationId, String status, User user) throws Exception {

        Donation donation = findDonationById(donationId);

        if (donation == null) {
            throw new Exception("Donation is not there");
        }

        if (donation.getDeleveryBoy().getId() != user.getId()) {
            throw new Exception("You cant upadte the donation Status");
        }

        donation.setDonationStatus(status);

        donationRepository.save(donation);

        user.setAvailableToDelevery(true);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(donation.getDonor().getEmail());
        emailDetails.setSubject("Donation Status");
        emailDetails.setMsgBody("Your Donation to a " + donation.getRecipient().getUserName() + " is " + status);
        String result = emailService.sendSimpleMail(emailDetails);
        System.out.println(result);
        userRepository.save(user);

        return donation;
    }

    @Override

    public Donation getDonation(Long donationId, User user) throws Exception {

        Donation donation = findDonationById(donationId);

        if (donation == null) {
            throw new Exception("Donation is not there");
        }

        if (donation.getDeleveryBoy().getId() != user.getId()) {
            throw new Exception("You cant upadte the donation Status");
        }

        return donation;
    }

    @Override
    public List<Donation> findDonationByDeleveryBoy(User user) {
        return donationRepository.findByDeleveryBoy(user);
    }

}
