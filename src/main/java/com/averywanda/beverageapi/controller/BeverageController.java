package com.averywanda.beverageapi.controller;

import com.averywanda.beverageapi.model.Beverage;
import com.averywanda.beverageapi.model.BeverageType;
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
     * Method calls the beverage service object to handle creating a new beverage type object.
     * @param beverageTypeObject
     * @return
     */
    @PostMapping(path = "/beverage-type/")
    public BeverageType createBeverageTypes(@RequestBody BeverageType beverageTypeObject) {
        return beverageService.createBeverageTypes(beverageTypeObject);
    }

    /**
     * Method calls BeverageType service object to handle getting all beverage types.
     * @return A list of BeverageType objects.
     */
    @GetMapping(path = "/beverage-type/")
    public List<BeverageType> getBeverageTypes() {
        return beverageService.getBeverageTypes();
    }

    /**
     * Method call BeverageType service object to handle getting a specific beverage type by Id
     * @param beverageTypeId
     * @return A BeverageType object.
     */
    @GetMapping(path = "/beverage-type/{beverageTypeId}/")
    public Optional<BeverageType> getBeverageType(@PathVariable Long beverageTypeId) {
        return beverageService.getBeverageType(beverageTypeId);
    }

    /**
     *
     * @param beverageTypeId
     * @param beverageTypeObject
     * @return
     */
    @PutMapping(path = "/beverage-type/{beverageTypeId}/")
    public BeverageType updateBeverageType(@PathVariable Long beverageTypeId, @RequestBody BeverageType beverageTypeObject) {
        return beverageService.updateBeverageType(beverageTypeId, beverageTypeObject);
    }

    /**
     * Method calls beverage service object to handle creating of a new beverage object.
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
    @GetMapping(path="/beverages/{beverageId}/")
    public Optional<Beverage> getBeverage(@PathVariable Long beverageId) {
        return beverageService.getBeverage(beverageId);
    }

    /**
     *
     * @param beverageId
     * @param beverageObject
     * @return
     */
//    @PutMapping(path = "/beverages/{beverageId}")
//    public Beverage updateBeverage(@PathVariable Long beverageId, @RequestBody Beverage beverageObject) {
//
//    }


}
