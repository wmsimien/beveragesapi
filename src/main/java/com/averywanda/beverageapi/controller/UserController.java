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

    /** POST /auth/users/register/
     * Calls method to create a registered user
     *
     * @param userObject User data passed into method.
     * @return User order.
     */
    @PostMapping(path="/register/")
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }

    /** POST /auth/users/login/
     * Calls method to login a registered user using  credentials passed-in.
     * @param loginRequest Contains registered user's email and password.
     * @return Returns authorization, JWT.
     */
    @PostMapping(path="/login/")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }
}
