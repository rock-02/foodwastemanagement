package com.example.backend.services;

import com.example.backend.entities.User;
import com.example.backend.entities.UserFeedBacks;

public interface UserService {

    
    public User findByjwt(String jwt) throws Exception;


    public User updateUser(User user,User reqUser) throws Exception;

    public UserFeedBacks userFeedback(User user,UserFeedBacks feedback) throws Exception;
    
}
