package com.example.backend.services;

import com.example.backend.entities.User;

public interface UserService {

    
    public User findByjwt(String jwt) throws Exception;

    
}
