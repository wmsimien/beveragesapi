package com.averywanda.beverageapi.controller;

import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.service.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class BeverageController {
    Logger logger = Logger.getLogger(BeverageService.class.getName());

    @Autowired
    BeverageService beverageService;
    @GetMapping(path = "/hello/")
    public String helloMsg() {
        return "This is a Test Hello, Folks!";
    }

    @PostMapping(path = "/beverages/")
    public Beverage createBeverage(@RequestBody Beverage beverageObject) {
        return beverageService.createBeverage(beverageObject);
    }
}
