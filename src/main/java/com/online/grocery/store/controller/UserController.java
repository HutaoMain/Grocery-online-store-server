package com.online.grocery.store.controller;

import com.online.grocery.store.model.User;
import com.online.grocery.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/userRole/{id}")
    private ResponseEntity<User> updateUserRoleById(@PathVariable String id, @RequestBody User user){
        User resUser = userService.updateUserRole(id, user);
        return ResponseEntity.ok(resUser);
    }

    @PutMapping("/changeShippingAddress/{email}")
    public ResponseEntity<User> updateAddress(@PathVariable("email") String email, @RequestBody User user) {
        User updateUserAddress = userService.updateShippingAddress(email, user);
        return ResponseEntity.ok(updateUserAddress);
    }
}
