package com.online.grocery.store.controller;

import com.online.grocery.store.model.User;
import com.online.grocery.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/list")
    public List<User> getListUserController() {
        return userService.getListUser();
    }

    @GetMapping("/{email}")
    private User getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }
}
