package com.averywanda.beverageapi.controller;


import com.averywanda.beverageapi.model.User;
import com.averywanda.beverageapi.model.request.LoginRequest;
import com.averywanda.beverageapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Calls method to create user
     * /auth/users/register/
     * @param userObject
     * @return
     */
    @PostMapping(path="/register/")
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }

    /**
     * Calls method to login user
     * @param loginRequest
     * @return
     */
    @PostMapping(path="/login/")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }
}
