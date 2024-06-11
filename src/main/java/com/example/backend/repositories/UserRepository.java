package com.example.backend.repositories;

import org.springframework.stereotype.Repository;

import com.example.backend.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.requestDonation = true")
    List<User> findUsersByRequestDonation();

    @Query("SELECT u FROM User u WHERE u.requestDonation = true ORDER BY u.requestDateTime ASC")
    List<User> findUserBasedOnRequestTimeAndRequestDonation();

    // @Query("SELECT u FROM User u WHERE u.isAvailableToDelevery = true AND u.role")
    @Query("SELECT u FROM User u WHERE u.role.name = 'DELEVERY_AGENT' AND u.isAvailableToDelevery = true")
    List<User> findUserByDeleveryBoyAndRole();



}
