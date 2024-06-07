package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.config.jwtProvider;
import com.example.backend.entities.Role;
import com.example.backend.entities.User;
import com.example.backend.models.LogginRequest;
import com.example.backend.models.RegisterRequest;
import com.example.backend.repositories.RoleRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signup")
    public AuthResponse RegisterUser(@RequestBody RegisterRequest user) throws Exception {

        User isUserExist = userRepository.findByEmail(user.getEmail());

        if (isUserExist != null) {
            throw new Exception("User Already Exist");
        }

        User newUser = new User();

        newUser.setUserName(user.getUserName());

        newUser.setEmail(user.getEmail());

        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = new Role();

        role.setName(user.getRole());

        Role savedRole = roleRepository.save(role);

        newUser.setRole(savedRole);

        newUser.setAddress(user.getAddress());

        User savedUser = userRepository.save(newUser);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(savedUser.getRole().getName()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword(), authorities);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "Regester Success");

        return authResponse;
    }

    @PostMapping("/login")
    public AuthResponse LoginUser(@RequestBody LogginRequest user) throws Exception {

        User isUserExist = userRepository.findByEmail(user.getEmail());

        if (isUserExist == null) {
            throw new Exception("User Not Found");
        }

        if (!passwordEncoder.matches(user.getPassword(), isUserExist.getPassword())) {
            throw new Exception("Invalid Password");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(isUserExist.getRole().getName()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(isUserExist.getEmail(),
                isUserExist.getPassword(), authorities);

        String token = jwtProvider.generateToken(authentication);

    
        AuthResponse authResponse = new AuthResponse(token, "Login Success");

        return authResponse;
    }
}
