package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.UserFeedBacks;

@Repository
public interface UserFeedBackRepository extends JpaRepository<UserFeedBacks, Long>{
    
}
