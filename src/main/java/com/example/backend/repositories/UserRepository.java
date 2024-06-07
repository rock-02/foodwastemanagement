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
    User findUserBasedOnRequestTimeAndRequestDonation();/

}
