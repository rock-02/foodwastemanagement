package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.User;
import com.example.backend.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User findUserByJwt(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByjwt(token);

        return user;
    }

    @PutMapping("/user/update")

    public User updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) throws Exception {

        User reqUser = userService.findByjwt(token);

        User updatedUser = userService.updateUser(user, reqUser);

        return updatedUser;
    }

}
