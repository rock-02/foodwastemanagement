package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.config.jwtProvider;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByjwt(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found");
        }

        return user;
    }

    @Override
    public User updateUser(User user, User reqUser) throws Exception {

        if (reqUser == null) {
            throw new Exception("User not found");
        }
        if (user.getAddress() != null) {
            if (user.getAddress().getState() == null && !user.getAddress().getState().isEmpty()) {
                reqUser.getAddress().setState(user.getAddress().getState());
            }
            if (user.getAddress().getCity() == null && !user.getAddress().getCity().isEmpty()) {
                reqUser.getAddress().setCity(user.getAddress().getCity());
            }
            if (user.getAddress().getPinCode() != null && !user.getAddress().getPinCode().isEmpty()) {
                reqUser.getAddress().setPinCode(user.getAddress().getPinCode());
            }
            if (user.getAddress().getDistrict() != null && !user.getAddress().getDistrict().isEmpty()) {
                reqUser.getAddress().setDistrict(user.getAddress().getDistrict());
            }
            if (user.getAddress().getArea() != null && !user.getAddress().getArea().isEmpty()) {
                reqUser.getAddress().setArea(user.getAddress().getArea());
            }
        }
        if (user.getProfilePicture() != null && !user.getProfilePicture().isEmpty())
            reqUser.setProfilePicture(user.getProfilePicture());

        if (user.getUserName() != null && !user.getUserName().isEmpty())
            reqUser.setUserName(user.getUserName());

        userRepository.save(reqUser);

        return reqUser;

    }

}
