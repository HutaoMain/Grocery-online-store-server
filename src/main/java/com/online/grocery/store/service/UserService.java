package com.online.grocery.store.service;

import com.online.grocery.store.model.User;
import com.online.grocery.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getListUser() {
        return userRepository.findAll();
    }

//    public void updateAddress(String email, User user) {
//        User setUser = userRepository.findByEmail(email);
//        setUser.setAddress(user.getAddress());
//        setUser.setCity(user.getCity());
//        setUser.setPostalCode(user.getPostalCode());
//        userRepository.save(setUser);
//    }
}
