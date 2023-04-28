package com.averywanda.beverageapi.controller;

import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.service.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    /**
     * Method calls service object to handle creation of a new beverage object.
     * @param beverageObject
     * @return A Beverage object.
     */
    @PostMapping(path = "/beverages/")
    public Beverage createBeverage(@RequestBody Beverage beverageObject) {
        return beverageService.createBeverage(beverageObject);
    }

    /**
     * Method calls service object to return a list of beverages.
     * @return A list of beverage objects.
     */
    @GetMapping(path = "/beverages/")
    public List<Beverage> getBeverages() {
        return beverageService.getBeverages();
    }

    /**
     * Method calls service object to get a specific beverage based on an Id passed in.
     * @param beverageId
     * @return
     */
    @GetMapping(path="/beverages/{beverageId}")
    public Optional<Beverage> getBeverage(@PathVariable Long beverageId) {
        return beverageService.getBeverage(beverageId);
    }


}
