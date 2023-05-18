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

    public User updateUserRole(String id, User user){
        User setUser = userRepository.findById(id).orElse(null);
        if(setUser != null){
            setUser.setUserRole(user.getUserRole());
            userRepository.save(setUser);
        }
        return setUser;
    }

    public User updateShippingAddress(String email, User user) {
        User setUser = userRepository.findByEmail(email);
        setUser.setStreet(user.getStreet());
        setUser.setBarangay(user.getBarangay());
        setUser.setPostalCode(user.getPostalCode());
        setUser.setMunicipality(user.getMunicipality());
        setUser.setCity(user.getCity());
        setUser.setContactNumber(user.getContactNumber());
        return userRepository.save(setUser);
    }
}
